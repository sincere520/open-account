package dev.practice.open.domain.account;


import dev.practice.open.domain.account.optiongroup.AccountOptionGroup;

import java.util.List;

public interface AccountOptionSeriesFactory {
    List<AccountOptionGroup> store(AccountCommand.RegisterAccountRequest request, Account account);
}
