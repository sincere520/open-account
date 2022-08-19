package dev.practice.open.interfaces.open;

import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.OpenInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OpenDtoMapper {

    @Mappings({
            @Mapping(source = "openedAt", target = "openedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    dev.practice.open.interfaces.open.OpenDto.Main of(OpenInfo.Main mainResult);

    dev.practice.open.interfaces.open.OpenDto.DeliveryInfo of(OpenInfo.DeliveryInfo deliveryResult);

    dev.practice.open.interfaces.open.OpenDto.OpenAccount of(OpenInfo.OpenAccount openAccountResult);

    OpenCommand.RegisterOpen of(dev.practice.open.interfaces.open.OpenDto.RegisterOpenRequest request);

    OpenCommand.RegisterOpenAccount of(dev.practice.open.interfaces.open.OpenDto.RegisterOpenAccount request);

    OpenCommand.RegisterOpenAccountOptionGroup of(dev.practice.open.interfaces.open.OpenDto.RegisterOpenAccountOptionGroupRequest request);

    OpenCommand.RegisterOpenAccountOption of(dev.practice.open.interfaces.open.OpenDto.RegisterOpenAccountOptionRequest request);

    dev.practice.open.interfaces.open.OpenDto.RegisterResponse of(String openToken);

    OpenCommand.PaymentRequest of(OpenDto.PaymentRequest request);
}
