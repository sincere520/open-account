package dev.practice.open.interfaces.open.gift;

import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.interfaces.open.gift.GiftOpenDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GiftOpenDtoMapper {

    @Mapping(source = "buyerUserId", target = "userId")
    OpenCommand.RegisterOpen of(dev.practice.open.interfaces.open.gift.GiftOpenDto.RegisterOpenRequest request);

    OpenCommand.RegisterOpenAccount of(dev.practice.open.interfaces.open.gift.GiftOpenDto.RegisterOpenAccount request);

    OpenCommand.RegisterOpenAccountOptionGroup of(dev.practice.open.interfaces.open.gift.GiftOpenDto.RegisterOpenAccountOptionGroupRequest request);

    OpenCommand.RegisterOpenAccountOption of(dev.practice.open.interfaces.open.gift.GiftOpenDto.RegisterOpenAccountOptionRequest request);

    dev.practice.open.interfaces.open.gift.GiftOpenDto.RegisterResponse of(String openToken);

    OpenCommand.PaymentRequest of(GiftOpenDto.PaymentRequest request);
}
