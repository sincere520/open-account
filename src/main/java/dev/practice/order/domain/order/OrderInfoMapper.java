package dev.practice.order.domain.order;

import dev.practice.order.domain.order.account.OrderAccount;
import dev.practice.order.domain.order.account.OrderAccountOption;
import dev.practice.order.domain.order.account.OrderAccountOptionGroup;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderInfoMapper {

    @Mappings({
            @Mapping(source = "order.id", target = "orderId"),
            @Mapping(source = "order.deliveryFragment", target = "deliveryInfo"),
            @Mapping(expression = "java(order.calculateTotalAmount())", target = "totalAmount"),
            @Mapping(expression = "java(order.getStatus().name())", target = "status"),
            @Mapping(expression = "java(order.getStatus().getDescription())", target = "statusDescription")
    })
    OrderInfo.Main of(Order order, List<OrderAccount> orderAccountList);

    @Mappings({
            @Mapping(expression = "java(orderAccount.getDeliveryStatus().name())", target = "deliveryStatus"),
            @Mapping(expression = "java(orderAccount.getDeliveryStatus().getDescription())", target = "deliveryStatusDescription"),
            @Mapping(expression = "java(orderAccount.calculateTotalAmount())", target = "totalAmount")
    })
    OrderInfo.OrderAccount of(OrderAccount orderAccount);

    OrderInfo.OrderAccountOptionGroup of(OrderAccountOptionGroup orderAccountOptionGroup);

    OrderInfo.OrderAccountOption of(OrderAccountOption orderAccountOption);
}
