package dev.practice.order.interfaces.order.remit;

import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RemitOrderDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderRequest {
        @NotNull(message = "senderUserId 는 필수값입니다")
        private Long senderUserId;

        //@NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        private List<RegisterOrderAccount> orderAccountList;

        private String receiverName = "TEMP_VALUE";
        private String receiverPhone = "TEMP_VALUE";
        private String receiverZipcode = "TEMP_VALUE";
        private String receiverAddress1 = "TEMP_VALUE";
        private String receiverAddress2 = "TEMP_VALUE";
        private String etcMessage = "TEMP_VALUE";
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

    @Getter
    @Setter
    @ToString
    public static class UpdateReceiverInfoReq {
        private String receiverName;
        private String receiverPhone;
        private String receiverZipcode;
        private String receiverAddress1;
        private String receiverAddress2;
        private String etcMessage;

        public OrderCommand.UpdateReceiverInfoRequest toCommand() {
            return OrderCommand.UpdateReceiverInfoRequest.builder()
                    .receiverName(receiverName)
                    .receiverPhone(receiverPhone)
                    .receiverZipcode(receiverZipcode)
                    .receiverAddress1(receiverAddress1)
                    .receiverAddress2(receiverAddress2)
                    .etcMessage(etcMessage)
                    .build();
        }
    }
}
