package dev.practice.order.interfaces.customer;

import dev.practice.order.domain.customer.CustomerInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CustomerDtoMapper {

    CustomerDto.Main of(CustomerInfo.Main mainResult);

}
