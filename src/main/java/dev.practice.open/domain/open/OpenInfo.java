package dev.practice.open.domain.open;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;

public class OpenInfo {

    @Getter
    @Builder
    @ToString
    public static class Main {
        private final Long openId;
        private final String openToken;
        private final Long userId;
        private final String payMethod;
        private final Long totalAmount;
        private final DeliveryInfo deliveryInfo;
        private final ZonedDateTime openedAt;
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
