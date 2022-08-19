package dev.practice.open.domain.account;

import dev.practice.open.domain.account.option.AccountOption;
import dev.practice.open.domain.account.optiongroup.AccountOptionGroup;
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
        private final Long accountPrice;
        private final Account.Status status;
        private final List<AccountOptionGroupInfo> accountOptionGroupList;

        public Main(Account account, List<AccountOptionGroupInfo> accountOptionGroupInfoList) {
            this.accountToken = account.getAccountToken();
            this.customerId = account.getCustomerId();
            this.accountName = account.getAccountName();
            this.accountPrice = account.getAccountPrice();
            this.status = account.getStatus();
            this.accountOptionGroupList = accountOptionGroupInfoList;
        }
    }

    @Getter
    @ToString
    public static class AccountOptionGroupInfo {
        private final Integer opening;
        private final String accountOptionGroupName;
        private final List<AccountOptionInfo> accountOptionList;

        public AccountOptionGroupInfo(AccountOptionGroup accountOptionGroup, List<AccountOptionInfo> accountOptionList) {
            this.opening = accountOptionGroup.getOpening();
            this.accountOptionGroupName = accountOptionGroup.getAccountOptionGroupName();
            this.accountOptionList = accountOptionList;
        }
    }

    @Getter
    @ToString
    public static class AccountOptionInfo {
        private final Integer opening;
        private final String accountOptionName;
        private final Long accountOptionPrice;

        public AccountOptionInfo(AccountOption accountOption) {
            this.opening = accountOption.getOpening();
            this.accountOptionName = accountOption.getAccountOptionName();
            this.accountOptionPrice = accountOption.getAccountOptionPrice();
        }
    }
}