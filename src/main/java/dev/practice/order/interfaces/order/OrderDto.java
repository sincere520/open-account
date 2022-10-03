package dev.practice.order.interfaces.order;

import dev.practice.order.domain.order.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderRequest {
        @NotNull(message = "userId 는 필수값입니다")
        private Long userId;

        //@NotBlank(message = "payMethod 는 필수값입니다")
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

        private List<RegisterOrderAccount> orderAccountList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderAccount {
        @NotNull(message = "orderCount 는 필수값입니다")
        private Integer orderCount;

        @NotBlank(message = "accountToken 는 필수값입니다")
        private String accountToken;

        @NotBlank(message = "accountName 는 필수값입니다")
        private String accountName;

        @NotNull(message = "accountAmt 는 필수값입니다")
        private Long accountAmt;

        private List<RegisterOrderAccountOptionGroupRequest> orderAccountOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderAccountOptionGroupRequest {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "accountOptionGroupName 는 필수값입니다")
        private String accountOptionGroupName;

        private List<RegisterOrderAccountOptionRequest> orderAccountOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderAccountOptionRequest {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "accountOptionName 는 필수값입니다")
        private String accountOptionName;

        @NotNull(message = "accountOptionAmt 는 필수값입니다")
        private Long accountOptionAmt;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterResponse {
        private final String orderToken;
    }

    @Getter
    @Setter
    @ToString
    public static class PaymentRequest {
        @NotBlank(message = "accountToken1 는 필수값입니다")
        private String accountToken1;

        @NotBlank(message = "accountToken2 는 필수값입니다")
        private String accountToken2;

        @NotBlank(message = "orderToken 는 필수값입니다")
        private String orderToken;

        //@NotNull(message = "payMethod 는 필수값입니다")
        private PayMethod payMethod;

        @NotNull(message = "amount 는 필수값입니다")
        private Long amount;

        @NotBlank(message = "orderDescription 는 필수값입니다")
        private String orderDescription;
    }

    // 조회
    @Getter
    @Builder
    @ToString
    public static class Main {
        private final String orderToken;
        private final Long userId;
        private final String payMethod;
        private final Long totalAmount;
        private final DeliveryInfo deliveryInfo;
        private final String orderedAt;
        private final String status;
        private final String statusDescription;
        private final List<OrderAccount> orderAccountList;
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
    public static class OrderAccount {
        private final Integer orderCount;
        private final Long customerId;
        private final Long accountId;
        private final String accountName;
        private final Long totalAmount;
        private final Long accountAmt;
        private final String deliveryStatus;
        private final String deliveryStatusDescription;
        private final List<OrderAccountOptionGroup> orderAccountOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderAccountOptionGroup {
        private final Integer ordering;
        private final String accountOptionGroupName;
        private final List<OrderAccountOption> orderAccountOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class OrderAccountOption {
        private final Integer ordering;
        private final String accountOptionName;
        private final Long accountOptionAmt;
    }
}
