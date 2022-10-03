package dev.practice.order.interfaces.account;

import dev.practice.order.application.account.AccountFacade;
import dev.practice.order.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountApiController {
    private final AccountFacade accountFacade;
    private final AccountDtoMapper accountDtoMapper;

    @PostMapping
    public CommonResponse registerAccount(@RequestBody @Valid AccountDto.RegisterAccountRequest request) {
        var customerToken = request.getCustomerToken();
        var accountCommand = accountDtoMapper.of(request);
        var accountToken = accountFacade.registerAccount(accountCommand, customerToken);
        var response = accountDtoMapper.of(accountToken);
        System.out.println("testtt");
        return CommonResponse.success(response);
    }

    @PostMapping("/change-available")
    public CommonResponse changeOnSaleAccount(@RequestBody @Valid AccountDto.ChangeStatusAccountRequest request) {
        var accountToken = request.getAccountToken();
        accountFacade.changeOnSaleAccount(accountToken);
        return CommonResponse.success("OK");
    }

    @PostMapping("/change-not-available")
    public CommonResponse changeEndOfSaleAccount(@RequestBody @Valid AccountDto.ChangeStatusAccountRequest request) {
        var accountToken = request.getAccountToken();
        accountFacade.changeEndOfSaleAccount(accountToken);
        return CommonResponse.success("OK");
    }

    @GetMapping("/{accountToken}")
    public CommonResponse retrieve(@PathVariable("accountToken") String accountToken) {
        var accountInfo = accountFacade.retrieveAccountInfo(accountToken);
        var response = accountDtoMapper.of(accountInfo);
        return CommonResponse.success(response);
    }
}
