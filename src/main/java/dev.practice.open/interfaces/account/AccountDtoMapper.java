package dev.practice.open.interfaces.account;

import dev.practice.open.domain.account.AccountCommand;
import dev.practice.open.domain.account.AccountInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AccountDtoMapper {

    // register
    @Mappings({@Mapping(source = "request.accountOptionGroupList", target = "accountOptionGroupRequestList")})
    AccountCommand.RegisterAccountRequest of(AccountDto.RegisterAccountRequest request);

    @Mappings({@Mapping(source = "accountOptionList", target = "accountOptionRequestList")})
    AccountCommand.RegisterAccountOptionGroupRequest of(AccountDto.RegisterAccountOptionGroupRequest request);

    AccountCommand.RegisterAccountOptionRequest of(AccountDto.RegisterAccountOptionRequest request);

    AccountDto.RegisterResponse of(String accountToken);

    // retrieve
    AccountDto.Main of(AccountInfo.Main main);

    AccountDto.AccountOptionGroupInfo of(AccountInfo.AccountOptionGroupInfo accountOptionGroup);

    AccountDto.AccountOptionInfo of(AccountInfo.AccountOptionInfo accountOption);
}
