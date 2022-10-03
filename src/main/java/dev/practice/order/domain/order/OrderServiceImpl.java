package dev.practice.order.domain.order;

import dev.practice.order.common.exception.BaseException;
import dev.practice.order.domain.account.AccountReader;
import dev.practice.order.domain.order.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static dev.practice.order.common.response.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderStore orderStore;
    private final OrderReader orderReader;
    private final OrderAccountSeriesFactory orderAccountSeriesFactory;
    private final PaymentProcessor paymentProcessor;
    private final OrderInfoMapper orderInfoMapper;
    private final AccountReader accountReader;

    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder requestOrder) {
        log.info("===OrderServiceImpl.registerOrder===");
        var account1 = accountReader.getAccountBy(requestOrder.getOrderAccountList().get(0).getAccountToken());
        if (requestOrder.getOrderAccountList().get(0).getAccountAmt() > account1.getAccountAmt()) throw new BaseException("계좌 잔액이 부족합니다.", NOT_ENOUGH_MONEY);

        Order order = orderStore.store(requestOrder.toEntity());
        orderAccountSeriesFactory.store(order, requestOrder);
        return order.getOrderToken();
    }

    @Override
    @Transactional
    public void paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        log.info("===OrderServiceImpl.paymentOrder===");
        var orderToken = paymentRequest.getOrderToken();
        var order = orderReader.getOrder(orderToken);
        paymentProcessor.pay(order, paymentRequest);
        order.orderComplete();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderInfo.Main retrieveOrder(String orderToken) {
        log.info("===OrderServiceImpl.retrieveOrder===");
        var order = orderReader.getOrder(orderToken);
        var orderAccountList = order.getOrderAccountList();
        return orderInfoMapper.of(order, orderAccountList);
    }

    @Override
    @Transactional
    public void updateReceiverInfo(String orderToken, OrderCommand.UpdateReceiverInfoRequest request) {
        log.info("===OrderServiceImpl.updateReceiverInfo===");
        var order = orderReader.getOrder(orderToken);
        order.updateDeliveryFragment(
                request.getReceiverName(),
                request.getReceiverPhone(),
                request.getReceiverZipcode(),
                request.getReceiverAddress1(),
                request.getReceiverAddress2(),
                request.getEtcMessage()
        );
        order.deliveryPrepare();
    }
}
