package dev.practice.order.domain.account;

import java.util.List;

public interface AccountReader {
    Account getAccountBy(String accountToken);
    List<AccountInfo.AccountOptionGroupInfo> getAccountOptionSeries(Account account);
}
