package dev.practice.order.domain.account;


import dev.practice.order.domain.account.optiongroup.AccountOptionGroup;

import java.util.List;

public interface AccountOptionSeriesFactory {
    List<AccountOptionGroup> store(AccountCommand.RegisterAccountRequest request, Account account);
}
