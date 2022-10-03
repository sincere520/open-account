package dev.practice.order.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderInfo {

    @Getter
    @Builder
    @ToString
    public static class Main {
        private final Long orderId;
        private final String orderToken;
        private final Long userId;
        private final String payMethod;
        private final Long totalAmount;
        private final DeliveryInfo deliveryInfo;
        private final ZonedDateTime orderedAt;
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
