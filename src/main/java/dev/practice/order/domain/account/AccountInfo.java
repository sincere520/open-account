package dev.practice.order.domain.account;

import dev.practice.order.domain.account.option.AccountOption;
import dev.practice.order.domain.account.optiongroup.AccountOptionGroup;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class AccountInfo {

    @Getter
    @ToString
    public static class Main {
        private final String accountToken;
        private final Long customerId;
        private final String accountName;
        private final Long accountAmt;
        private final Account.Status status;
        private final List<AccountOptionGroupInfo> accountOptionGroupList;

        public Main(Account account, List<AccountOptionGroupInfo> accountOptionGroupInfoList) {
            this.accountToken = account.getAccountToken();
            this.customerId = account.getCustomerId();
            this.accountName = account.getAccountName();
            this.accountAmt = account.getAccountAmt();
            this.status = account.getStatus();
            this.accountOptionGroupList = accountOptionGroupInfoList;
        }
    }

    @Getter
    @ToString
    public static class AccountOptionGroupInfo {
        private final Integer ordering;
        private final String accountOptionGroupName;
        private final List<AccountOptionInfo> accountOptionList;

        public AccountOptionGroupInfo(AccountOptionGroup accountOptionGroup, List<AccountOptionInfo> accountOptionList) {
            this.ordering = accountOptionGroup.getOrdering();
            this.accountOptionGroupName = accountOptionGroup.getAccountOptionGroupName();
            this.accountOptionList = accountOptionList;
        }
    }

    @Getter
    @ToString
    public static class AccountOptionInfo {
        private final Integer ordering;
        private final String accountOptionName;
        private final Long accountOptionAmt;

        public AccountOptionInfo(AccountOption accountOption) {
            this.ordering = accountOption.getOrdering();
            this.accountOptionName = accountOption.getAccountOptionName();
            this.accountOptionAmt = accountOption.getAccountOptionAmt();
        }
    }
}