package dev.practice.open.interfaces.open.gift;

import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class GiftOpenDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterOpenRequest {
        @NotNull(message = "buyerUserId 는 필수값입니다")
        private Long buyerUserId;

        @NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        private List<RegisterOpenAccount> openAccountList;

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

        public OpenCommand.UpdateReceiverInfoRequest toCommand() {
            return OpenCommand.UpdateReceiverInfoRequest.builder()
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
