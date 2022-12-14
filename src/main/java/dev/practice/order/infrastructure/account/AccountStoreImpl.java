package dev.practice.order.infrastructure.account;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.account.Account;
import dev.practice.order.domain.account.AccountStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountStoreImpl implements AccountStore {
    private final AccountRepository accountRepository;

    @Override
    public Account store(Account account) {
        validCheck(account);
        return accountRepository.save(account);
    }

    private void validCheck(Account account) {
        if (StringUtils.isEmpty(account.getAccountToken())) throw new InvalidParamException("Account.accountToken");
        if (StringUtils.isEmpty(account.getAccountName())) throw new InvalidParamException("Account.accountName");
        if (account.getCustomerId() == null) throw new InvalidParamException("Account.customerId");
        if (account.getAccountAmt() == null) throw new InvalidParamException("Account.accountAmt");
        if (account.getStatus() == null) throw new InvalidParamException("Account.status");
    }
}
