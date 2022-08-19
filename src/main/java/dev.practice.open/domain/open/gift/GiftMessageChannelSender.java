package dev.practice.open.domain.open.gift;

import dev.practice.open.domain.open.gift.GiftPaymentCompleteMessage;

public interface GiftMessageChannelSender {
    void paymentComplete(GiftPaymentCompleteMessage message);
}
