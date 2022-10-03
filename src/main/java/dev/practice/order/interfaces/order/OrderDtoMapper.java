package dev.practice.order.interfaces.order;

import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    @Mappings({
            @Mapping(source = "orderedAt", target = "orderedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    OrderDto.Main of(OrderInfo.Main mainResult);

    OrderDto.DeliveryInfo of(OrderInfo.DeliveryInfo deliveryResult);

    OrderDto.OrderAccount of(OrderInfo.OrderAccount orderAccountResult);

    OrderCommand.RegisterOrder of(OrderDto.RegisterOrderRequest request);

    OrderCommand.RegisterOrderAccount of(OrderDto.RegisterOrderAccount request);

    OrderCommand.RegisterOrderAccountOptionGroup of(OrderDto.RegisterOrderAccountOptionGroupRequest request);

    OrderCommand.RegisterOrderAccountOption of(OrderDto.RegisterOrderAccountOptionRequest request);

    OrderDto.RegisterResponse of(String orderToken);

    OrderCommand.PaymentRequest of(OrderDto.PaymentRequest request);
}
