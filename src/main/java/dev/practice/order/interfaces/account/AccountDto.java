package dev.practice.order.interfaces.account;

import dev.practice.order.domain.account.Account;
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
        private Long accountAmt;
        private List<RegisterAccountOptionGroupRequest> accountOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterAccountOptionGroupRequest {
        private Integer ordering;
        private String accountOptionGroupName;
        private List<RegisterAccountOptionRequest> accountOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterAccountOptionRequest {
        private Integer ordering;
        private String accountOptionName;
        private Long accountOptionAmt;
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
        private final Long accountAmt;
        private final Account.Status status;
        private final List<AccountOptionGroupInfo> accountOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class AccountOptionGroupInfo {
        private final Integer ordering;
        private final String accountOptionGroupName;
        private final List<AccountOptionInfo> accountOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class AccountOptionInfo {
        private final Integer ordering;
        private final String accountOptionName;
        private final Long accountOptionAmt;
    }
}
