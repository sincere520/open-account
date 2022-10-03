package dev.practice.order.domain.order.remit;

import dev.practice.order.domain.order.OrderCommand;

public interface RemitOrderService {
    void paymentOrder(OrderCommand.PaymentRequest paymentRequest);
}
