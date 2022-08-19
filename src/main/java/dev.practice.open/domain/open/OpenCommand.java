package dev.practice.open.domain.open;

import dev.practice.open.domain.account.Account;
import dev.practice.open.domain.open.fragment.DeliveryFragment;
import dev.practice.open.domain.open.account.OpenAccount;
import dev.practice.open.domain.open.account.OpenAccountOption;
import dev.practice.open.domain.open.account.OpenAccountOptionGroup;
import dev.practice.open.domain.open.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class OpenCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterOpen {
        private final Long userId;
        private final String payMethod;
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
        private final List<RegisterOpenAccount> openAccountList;

        public dev.practice.open.domain.open.Open toEntity() {
            var deliveryFragment = DeliveryFragment.builder()
                    .receiverName(receiverName)
                    .receiverPhone(receiverPhone)
                    .receiverZipcode(receiverZipcode)
                    .receiverAddress1(receiverAddress1)
                    .receiverAddress2(receiverAddress2)
                    .etcMessage(etcMessage)
                    .build();

            return dev.practice.open.domain.open.Open.builder()
                    .userId(userId)
                    .payMethod(payMethod)
                    .deliveryFragment(deliveryFragment)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOpenAccount {
        private final Integer openCount;
        private final String accountToken;
        private final String accountName;
        private final Long accountPrice;
        private final List<RegisterOpenAccountOptionGroup> openAccountOptionGroupList;

        public OpenAccount toEntity(Open open, Account account) {
            return OpenAccount.builder()
                    .open(open)
                    .openCount(openCount)
                    .customerId(account.getCustomerId())
                    .accountId(account.getId())
                    .accountToken(accountToken)
                    .accountName(accountName)
                    .accountPrice(accountPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOpenAccountOptionGroup {
        private final Integer opening;
        private final String accountOptionGroupName;
        private final List<RegisterOpenAccountOption> openAccountOptionList;

        public OpenAccountOptionGroup toEntity(OpenAccount openAccount) {
            return OpenAccountOptionGroup.builder()
                    .openAccount(openAccount)
                    .opening(opening)
                    .accountOptionGroupName(accountOptionGroupName)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOpenAccountOption {
        private final Integer opening;
        private final String accountOptionName;
        private final Long accountOptionPrice;

        public OpenAccountOption toEntity(OpenAccountOptionGroup openAccountOptionGroup) {
            return OpenAccountOption.builder()
                    .openAccountOptionGroup(openAccountOptionGroup)
                    .opening(opening)
                    .accountOptionName(accountOptionName)
                    .accountOptionPrice(accountOptionPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class PaymentRequest {
        private final String openToken;
        private final Long amount;
        private final PayMethod payMethod;
    }

    @Getter
    @Builder
    @ToString
    public static class UpdateReceiverInfoRequest {
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
    }
}