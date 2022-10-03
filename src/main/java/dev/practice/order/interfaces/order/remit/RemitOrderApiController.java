package dev.practice.order.interfaces.order.remit;

import dev.practice.order.application.order.OrderFacade;
import dev.practice.order.application.order.remit.RemitFacade;
import dev.practice.order.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/remit-orders")
public class RemitOrderApiController {
    private final OrderFacade orderFacade;
    private final RemitFacade remitFacade;
    private final RemitOrderDtoMapper remitOrderDtoMapper;

    @PostMapping("/init")
    public CommonResponse registerOrder(@RequestBody @Valid RemitOrderDto.RegisterOrderRequest request) {
        var orderCommand = remitOrderDtoMapper.of(request);
        var result = orderFacade.registerOrder(orderCommand);
        var response = remitOrderDtoMapper.of(result);
        return CommonResponse.success(response);
    }

    @PostMapping("/payment-order")
    public CommonResponse paymentOrder(@RequestBody @Valid RemitOrderDto.PaymentRequest request) {
        var orderPaymentCommand = remitOrderDtoMapper.of(request);
        remitFacade.paymentOrder(orderPaymentCommand);
        return CommonResponse.success("OK");
    }

    @PostMapping("/{orderToken}/update-receiver-info")
    public CommonResponse updateReceiverInfo(
            @PathVariable String orderToken,
            @RequestBody @Valid RemitOrderDto.UpdateReceiverInfoReq request
    ) {
        var orderCommand = request.toCommand();
        orderFacade.updateReceiverInfo(orderToken, orderCommand);
        return CommonResponse.success("OK");
    }
}
