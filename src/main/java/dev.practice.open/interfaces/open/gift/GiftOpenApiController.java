package dev.practice.open.interfaces.open.gift;

import dev.practice.open.application.open.OpenFacade;
import dev.practice.open.application.open.gift.GiftFacade;
import dev.practice.open.common.response.CommonResponse;
import dev.practice.open.interfaces.open.gift.GiftOpenDto;
import dev.practice.open.interfaces.open.gift.GiftOpenDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gift-opens")
public class GiftOpenApiController {
    private final OpenFacade openFacade;
    private final GiftFacade giftFacade;
    private final GiftOpenDtoMapper giftOpenDtoMapper;

    @PostMapping("/init")
    public CommonResponse registerOpen(@RequestBody @Valid dev.practice.open.interfaces.open.gift.GiftOpenDto.RegisterOpenRequest request) {
        var openCommand = giftOpenDtoMapper.of(request);
        var result = openFacade.registerOpen(openCommand);
        var response = giftOpenDtoMapper.of(result);
        return CommonResponse.success(response);
    }

    @PostMapping("/payment-open")
    public CommonResponse paymentOpen(@RequestBody @Valid dev.practice.open.interfaces.open.gift.GiftOpenDto.PaymentRequest request) {
        var openPaymentCommand = giftOpenDtoMapper.of(request);
        giftFacade.paymentOpen(openPaymentCommand);
        return CommonResponse.success("OK");
    }

    @PostMapping("/{openToken}/update-receiver-info")
    public CommonResponse updateReceiverInfo(
            @PathVariable String openToken,
            @RequestBody @Valid GiftOpenDto.UpdateReceiverInfoReq request
    ) {
        var openCommand = request.toCommand();
        openFacade.updateReceiverInfo(openToken, openCommand);
        return CommonResponse.success("OK");
    }
}
