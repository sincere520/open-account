package dev.practice.order.domain.order;

import dev.practice.order.domain.order.account.OrderAccount;
import dev.practice.order.domain.order.account.OrderAccountOption;
import dev.practice.order.domain.order.account.OrderAccountOptionGroup;

public interface OrderStore {
    Order store(Order order);
    OrderAccount store(OrderAccount orderAccount);
    OrderAccountOptionGroup store(OrderAccountOptionGroup orderAccountOptionGroup);
    OrderAccountOption store(OrderAccountOption orderAccountOption);
}
