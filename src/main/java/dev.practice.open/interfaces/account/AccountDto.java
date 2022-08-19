package dev.practice.open.interfaces.account;

import dev.practice.open.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class AccountDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterAccountRequest {
        private String customerToken;
        private String accountName;
        private Long accountPrice;
        private List<RegisterAccountOptionGroupRequest> accountOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterAccountOptionGroupRequest {
        private Integer opening;
        private String accountOptionGroupName;
        private List<RegisterAccountOptionRequest> accountOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterAccountOptionRequest {
        private Integer opening;
        private String accountOptionName;
        private Long accountOptionPrice;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterResponse {
        private final String accountToken;
    }

    @Getter
    @Setter
    @ToString
    public static class ChangeStatusAccountRequest {
        private String accountToken;
    }

    @Getter
    @Builder
    @ToString
    public static class Main {
        private final String accountToken;
        private final Long customerId;
        private final String accountName;
        private final Long accountPrice;
        private final Account.Status status;
        private final List<AccountOptionGroupInfo> accountOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class AccountOptionGroupInfo {
        private final Integer opening;
        private final String accountOptionGroupName;
        private final List<AccountOptionInfo> accountOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class AccountOptionInfo {
        private final Integer opening;
        private final String accountOptionName;
        private final Long accountOptionPrice;
    }
}
