package dev.practice.order.domain.order;

import dev.practice.order.domain.account.Account;
import dev.practice.order.domain.order.account.OrderAccount;
import dev.practice.order.domain.order.account.OrderAccountOption;
import dev.practice.order.domain.order.account.OrderAccountOptionGroup;
import dev.practice.order.domain.order.fragment.DeliveryFragment;
import dev.practice.order.domain.order.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class OrderCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterOrder {
        private final Long userId;
        private final String payMethod;
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
        private final List<RegisterOrderAccount> orderAccountList;

        public Order toEntity() {
            var deliveryFragment = DeliveryFragment.builder()
                    .receiverName(receiverName)
                    .receiverPhone(receiverPhone)
                    .receiverZipcode(receiverZipcode)
                    .receiverAddress1(receiverAddress1)
                    .receiverAddress2(receiverAddress2)
                    .etcMessage(etcMessage)
                    .build();

            return Order.builder()
                    .userId(userId)
                    .payMethod(payMethod)
                    .deliveryFragment(deliveryFragment)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderAccount {
        private final Integer orderCount;
        private final String accountToken;
        private final String accountName;
        private final Long accountAmt;
        private final List<RegisterOrderAccountOptionGroup> orderAccountOptionGroupList;

        public OrderAccount toEntity(Order order, Account account) {
            return OrderAccount.builder()
                    .order(order)
                    .orderCount(orderCount)
                    .customerId(account.getCustomerId())
                    .accountId(account.getId())
                    .accountToken(accountToken)
                    .accountName(accountName)
                    .accountAmt(accountAmt)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderAccountOptionGroup {
        private final Integer ordering;
        private final String accountOptionGroupName;
        private final List<RegisterOrderAccountOption> orderAccountOptionList;

        public OrderAccountOptionGroup toEntity(OrderAccount orderAccount) {
            return OrderAccountOptionGroup.builder()
                    .orderAccount(orderAccount)
                    .ordering(ordering)
                    .accountOptionGroupName(accountOptionGroupName)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderAccountOption {
        private final Integer ordering;
        private final String accountOptionName;
        private final Long accountOptionAmt;

        public OrderAccountOption toEntity(OrderAccountOptionGroup orderAccountOptionGroup) {
            return OrderAccountOption.builder()
                    .orderAccountOptionGroup(orderAccountOptionGroup)
                    .ordering(ordering)
                    .accountOptionName(accountOptionName)
                    .accountOptionAmt(accountOptionAmt)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class PaymentRequest {
        private final String orderToken;
        private final String accountToken1;
        private final String accountToken2;
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