package dev.practice.open.domain.account;

import dev.practice.open.domain.customer.CustomerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
