package dev.practice.open.domain.account;

import dev.practice.open.domain.account.option.AccountOption;
import dev.practice.open.domain.account.optiongroup.AccountOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class AccountCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterAccountRequest {
        private final String accountName;
        private final Long accountPrice;
        private final List<RegisterAccountOptionGroupRequest> accountOptionGroupRequestList; // ex) 색상, 사이즈

        public Account toEntity(Long customerId) {
            return Account.builder()
                    .customerId(customerId)
                    .accountName(accountName)
                    .accountPrice(accountPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterAccountOptionGroupRequest {  // ex) 색상
        private final Integer opening;
        private final String accountOptionGroupName;
        private final List<RegisterAccountOptionRequest> accountOptionRequestList;  // ex) R, B, W

        public AccountOptionGroup toEntity(Account account) {
            return AccountOptionGroup.builder()
                    .account(account)
                    .opening(opening)
                    .accountOptionGroupName(accountOptionGroupName)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterAccountOptionRequest {
        private final Integer opening;
        private final String accountOptionName;
        private final Long accountOptionPrice;

    public AccountOption toEntity(AccountOptionGroup accountOptionGroup) {
            return AccountOption.builder()
                    .accountOptionGroup(accountOptionGroup)
                    .opening(opening)
                    .accountOptionName(accountOptionName)
                    .accountOptionPrice(accountOptionPrice)
                    .build();
        }
    }
}
