package dev.practice.order.domain.order.remit;

public interface RemitMessageChannelSender {
    void paymentComplete(RemitPaymentCompleteMessage message);
}
