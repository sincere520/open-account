package dev.practice.order.domain.order.remit;

import dev.practice.order.domain.order.payment.PaymentProcessor;
import dev.practice.order.common.exception.IllegalStatusException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemitOrderServiceImpl implements RemitOrderService {
    private final OrderReader orderReader;
    private final PaymentProcessor paymentProcessor;
    private final RemitMessageChannelSender remitMessageChannelSender;

    @Override
    @Transactional
    public void paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        log.info("RemitOrderService.paymentOrder request = {}", paymentRequest);
        var order = orderReader.getOrder(paymentRequest.getOrderToken());

        // 아래 로직을 추가하면, paymentProcessor.pay 실행 이후의 보상 트랜잭션 발생을 막을 수 있다
        if (order.getStatus() != Order.Status.INIT) throw new IllegalStatusException();

        //paymentProcessor.pay(order, paymentRequest);
        //order.orderComplete();

        remitMessageChannelSender.paymentComplete(new RemitPaymentCompleteMessage(order.getOrderToken()));
    }
}
