package dev.practice.open.interfaces.customer;

import dev.practice.open.application.customer.CustomerFacade;
import dev.practice.open.common.response.CommonResponse;
import dev.practice.open.interfaces.account.AccountDtoMapper;
import dev.practice.open.interfaces.customer.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerApiController {
    private final CustomerFacade customerFacade;

    private final CustomerDtoMapper customerDtoMapper;
    @PostMapping
    public CommonResponse registerCustomer(@RequestBody @Valid dev.practice.open.interfaces.customer.CustomerDto.RegisterRequest request) {
        var command = request.toCommand();
        var customerInfo = customerFacade.registerCustomer(command);
        var response = new CustomerDto.RegisterResponse(customerInfo);
        return CommonResponse.success(response);
    }
    @GetMapping("/{customerToken}")
    public CommonResponse retrieve(@PathVariable("customerToken") String customerToken) {
        var customerInfo = customerFacade.retrieveCustomerInfo(customerToken);
        var response = customerDtoMapper.of(customerInfo);
        return CommonResponse.success(response);
    }
}
