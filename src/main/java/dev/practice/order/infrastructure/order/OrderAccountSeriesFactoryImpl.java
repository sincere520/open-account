package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.account.AccountReader;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderAccountSeriesFactory;
import dev.practice.order.domain.order.OrderStore;
import dev.practice.order.domain.order.account.OrderAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderAccountSeriesFactoryImpl implements OrderAccountSeriesFactory {
    private final AccountReader accountReader;
    private final OrderStore orderStore;

    @Override
    public List<OrderAccount> store(Order order, OrderCommand.RegisterOrder requestOrder) {
        return requestOrder.getOrderAccountList().stream()
                .map(orderAccountRequest -> {
                    var account = accountReader.getAccountBy(orderAccountRequest.getAccountToken());
                    var initOrderAccount = orderAccountRequest.toEntity(order, account);
                    var orderAccount = orderStore.store(initOrderAccount);

                    orderAccountRequest.getOrderAccountOptionGroupList().forEach(orderAccountOptionGroupRequest -> {
                        var initOrderAccountOptionGroup = orderAccountOptionGroupRequest.toEntity(orderAccount);
                        var orderAccountOptionGroup = orderStore.store(initOrderAccountOptionGroup);

                        orderAccountOptionGroupRequest.getOrderAccountOptionList().forEach(orderAccountOptionRequest -> {
                            var initOrderAccountOption = orderAccountOptionRequest.toEntity(orderAccountOptionGroup);
                            orderStore.store(initOrderAccountOption);
                        });
                    });
                    return orderAccount;
                }).collect(Collectors.toList());
    }
}
