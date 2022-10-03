package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderStore;
import dev.practice.order.domain.order.account.OrderAccount;
import dev.practice.order.domain.order.account.OrderAccountOption;
import dev.practice.order.domain.order.account.OrderAccountOptionGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {
    private final OrderRepository orderRepository;
    private final OrderAccountRepository orderAccountRepository;
    private final OrderAccountOptionGroupRepository orderAccountOptionGroupRepository;
    private final OrderAccountOptionRepository orderAccountOptionRepository;

    @Override
    public Order store(Order order) {
        log.info("===OrderStoreImpl.Order store===");
        return orderRepository.save(order);
    }

    @Override
    public OrderAccount store(OrderAccount orderAccount) {
        log.info("===OrderStoreImpl.OrderAccount store===");
        return orderAccountRepository.save(orderAccount);
    }

    @Override
    public OrderAccountOptionGroup store(OrderAccountOptionGroup orderAccountOptionGroup) {
        log.info("===OrderStoreImpl.OrderAccountOptionGroup store===");
        return orderAccountOptionGroupRepository.save(orderAccountOptionGroup);
    }

    @Override
    public OrderAccountOption store(OrderAccountOption orderAccountOption) {
        log.info("===OrderStoreImpl.OrderAccountOption store===");
        return orderAccountOptionRepository.save(orderAccountOption);
    }
}
