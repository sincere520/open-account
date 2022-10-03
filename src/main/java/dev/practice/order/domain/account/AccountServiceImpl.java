package dev.practice.order.domain.account;

import dev.practice.order.common.exception.*;
import dev.practice.order.domain.customer.CustomerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static dev.practice.order.common.response.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final CustomerReader customerReader;
    private final AccountStore accountStore;
    private final AccountReader accountReader;
    private final AccountOptionSeriesFactory accountOptionSeriesFactory;

    @Override
    @Transactional
    public String registerAccount(AccountCommand.RegisterAccountRequest command, String customerToken) {
        var customer = customerReader.getCustomer(customerToken);
        var initAccount = command.toEntity(customer.getId());
        var account = accountStore.store(initAccount);
        accountOptionSeriesFactory.store(command, account);
        return account.getAccountToken();
    }
    public void updateAccount(String accountToken1, String accountToken2, long amount) {
        var account1 = accountReader.getAccountBy(accountToken1);
        var account2 = accountReader.getAccountBy(accountToken2);

        if (amount > account1.getAccountAmt()) throw new BaseException("계좌1 잔액이 부족합니다.", NOT_ENOUGH_MONEY);
        if (account1.getStatus().equals("NOT_AVAILABLE")) throw new BaseException("계좌1 상태가 비활성화 상태 입니다.", NOT_AVAILABLE);
        if (account2.getStatus().equals("NOT_AVAILABLE")) throw new BaseException("계좌2 상태가 비활성화 상태 입니다.", NOT_AVAILABLE);

        account1.changeAmount(account1.getAccountAmt()-amount);
        account2.changeAmount(account2.getAccountAmt()+amount);

    }
    @Override
    @Transactional
    public void changeOnSale(String accountToken) {
        var account = accountReader.getAccountBy(accountToken);
        account.changeOnSale();
    }

    @Override
    @Transactional
    public void changeEndOfSale(String accountToken) {
        var account = accountReader.getAccountBy(accountToken);
        account.changeEndOfSale();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountInfo.Main retrieveAccountInfo(String accountToken) {
        var account = accountReader.getAccountBy(accountToken);
        var accountOptionGroupInfoList = accountReader.getAccountOptionSeries(account);
        return new AccountInfo.Main(account, accountOptionGroupInfoList);
    }
}
