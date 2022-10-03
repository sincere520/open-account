package dev.practice.order.domain.order;

import dev.practice.order.domain.order.account.OrderAccount;

import java.util.List;

public interface OrderAccountSeriesFactory {
    List<OrderAccount> store(Order order, OrderCommand.RegisterOrder requestOrder);
}
