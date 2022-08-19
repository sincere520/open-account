package dev.practice.open.interfaces.open;

import dev.practice.open.domain.open.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OpenDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterOpenRequest {
        @NotNull(message = "userId 는 필수값입니다")
        private Long userId;

        @NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        @NotBlank(message = "receiverName 는 필수값입니다")
        private String receiverName;

        @NotBlank(message = "receiverPhone 는 필수값입니다")
        private String receiverPhone;

        @NotBlank(message = "receiverZipcode 는 필수값입니다")
        private String receiverZipcode;

        @NotBlank(message = "receiverAddress1 는 필수값입니다")
        private String receiverAddress1;

        @NotBlank(message = "receiverAddress2 는 필수값입니다")
        private String receiverAddress2;

        @NotBlank(message = "etcMessage 는 필수값입니다")
        private String etcMessage;

        private List<RegisterOpenAccount> openAccountList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOpenAccount {
        @NotNull(message = "openCount 는 필수값입니다")
        private Integer openCount;

        @NotBlank(message = "accountToken 는 필수값입니다")
        private String accountToken;

        @NotBlank(message = "accountName 는 필수값입니다")
        private String accountName;

        @NotNull(message = "accountPrice 는 필수값입니다")
        private Long accountPrice;

        private List<RegisterOpenAccountOptionGroupRequest> openAccountOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOpenAccountOptionGroupRequest {
        @NotNull(message = "opening 는 필수값입니다")
        private Integer opening;

        @NotBlank(message = "accountOptionGroupName 는 필수값입니다")
        private String accountOptionGroupName;

        private List<RegisterOpenAccountOptionRequest> openAccountOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOpenAccountOptionRequest {
        @NotNull(message = "opening 는 필수값입니다")
        private Integer opening;

        @NotBlank(message = "accountOptionName 는 필수값입니다")
        private String accountOptionName;

        @NotNull(message = "accountOptionPrice 는 필수값입니다")
        private Long accountOptionPrice;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterResponse {
        private final String openToken;
    }

    @Getter
    @Setter
    @ToString
    public static class PaymentRequest {
        @NotBlank(message = "openToken 는 필수값입니다")
        private String openToken;

        @NotNull(message = "payMethod 는 필수값입니다")
        private PayMethod payMethod;

        @NotNull(message = "amount 는 필수값입니다")
        private Long amount;

        @NotBlank(message = "openDescription 는 필수값입니다")
        private String openDescription;
    }

    // 조회
    @Getter
    @Builder
    @ToString
    public static class Main {
        private final String openToken;
        private final Long userId;
        private final String payMethod;
        private final Long totalAmount;
        private final DeliveryInfo deliveryInfo;
        private final String openedAt;
        private final String status;
        private final String statusDescription;
        private final List<OpenAccount> openAccountList;
    }

    @Getter
    @Builder
    @ToString
    public static class DeliveryInfo {
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
    }

    @Getter
    @Builder
    @ToString
    public static class OpenAccount {
        private final Integer openCount;
        private final Long customerId;
        private final Long accountId;
        private final String accountName;
        private final Long totalAmount;
        private final Long accountPrice;
        private final String deliveryStatus;
        private final String deliveryStatusDescription;
        private final List<OpenAccountOptionGroup> openAccountOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class OpenAccountOptionGroup {
        private final Integer opening;
        private final String accountOptionGroupName;
        private final List<OpenAccountOption> openAccountOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class OpenAccountOption {
        private final Integer opening;
        private final String accountOptionName;
        private final Long accountOptionPrice;
    }
}
