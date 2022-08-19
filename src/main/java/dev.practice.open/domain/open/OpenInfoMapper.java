package dev.practice.open.domain.open;

import dev.practice.open.domain.open.account.OpenAccount;
import dev.practice.open.domain.open.account.OpenAccountOption;
import dev.practice.open.domain.open.account.OpenAccountOptionGroup;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OpenInfoMapper {

    @Mappings({
            @Mapping(source = "open.id", target = "openId"),
            @Mapping(source = "open.deliveryFragment", target = "deliveryInfo"),
            @Mapping(expression = "java(open.calculateTotalAmount())", target = "totalAmount"),
            @Mapping(expression = "java(open.getStatus().name())", target = "status"),
            @Mapping(expression = "java(open.getStatus().getDescription())", target = "statusDescription")
    })
    dev.practice.open.domain.open.OpenInfo.Main of(Open open, List<OpenAccount> openAccountList);

    @Mappings({
            @Mapping(expression = "java(openAccount.getDeliveryStatus().name())", target = "deliveryStatus"),
            @Mapping(expression = "java(openAccount.getDeliveryStatus().getDescription())", target = "deliveryStatusDescription"),
            @Mapping(expression = "java(openAccount.calculateTotalAmount())", target = "totalAmount")
    })
    dev.practice.open.domain.open.OpenInfo.OpenAccount of(OpenAccount openAccount);

    dev.practice.open.domain.open.OpenInfo.OpenAccountOptionGroup of(OpenAccountOptionGroup openAccountOptionGroup);

    OpenInfo.OpenAccountOption of(OpenAccountOption openAccountOption);
}
