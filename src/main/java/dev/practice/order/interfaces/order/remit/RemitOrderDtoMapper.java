package dev.practice.order.interfaces.order.remit;

import dev.practice.order.domain.order.OrderCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RemitOrderDtoMapper {

    @Mapping(source = "senderUserId", target = "userId")
    OrderCommand.RegisterOrder of(RemitOrderDto.RegisterOrderRequest request);

    OrderCommand.RegisterOrderAccount of(RemitOrderDto.RegisterOrderAccount request);

    OrderCommand.RegisterOrderAccountOptionGroup of(RemitOrderDto.RegisterOrderAccountOptionGroupRequest request);

    OrderCommand.RegisterOrderAccountOption of(RemitOrderDto.RegisterOrderAccountOptionRequest request);

    RemitOrderDto.RegisterResponse of(String orderToken);

    OrderCommand.PaymentRequest of(RemitOrderDto.PaymentRequest request);
}
