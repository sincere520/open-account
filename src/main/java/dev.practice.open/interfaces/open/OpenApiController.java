package dev.practice.open.interfaces.open;

import dev.practice.open.application.open.OpenFacade;
import dev.practice.open.common.response.CommonResponse;
import dev.practice.open.interfaces.open.OpenDto;
import dev.practice.open.interfaces.open.OpenDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/opens")
public class OpenApiController {
    private final OpenFacade openFacade;
    private final OpenDtoMapper openDtoMapper;

    @PostMapping("/init")
    public CommonResponse registerOpen(@RequestBody @Valid dev.practice.open.interfaces.open.OpenDto.RegisterOpenRequest request) {
        var openCommand = openDtoMapper.of(request);
        var openToken = openFacade.registerOpen(openCommand);
        var response = openDtoMapper.of(openToken);
        return CommonResponse.success(response);
    }

    @GetMapping("/{openToken}")
    public CommonResponse retrieveOpen(@PathVariable String openToken) {
        var openResult = openFacade.retrieveOpen(openToken);
        var response = openDtoMapper.of(openResult);
        return CommonResponse.success(response);
    }

    @PostMapping("/payment-open")
    public CommonResponse paymentOpen(@RequestBody @Valid OpenDto.PaymentRequest paymentRequest) {
        var paymentCommand = openDtoMapper.of(paymentRequest);
        openFacade.paymentOpen(paymentCommand);
        return CommonResponse.success("OK");
    }
}
