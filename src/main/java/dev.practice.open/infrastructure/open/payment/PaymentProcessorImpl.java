package dev.practice.open.infrastructure.open.payment;

import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.payment.PaymentProcessor;
import dev.practice.open.domain.open.payment.validator.PaymentValidator;
import dev.practice.open.infrastructure.open.payment.PaymentApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {
    private final List<PaymentValidator> paymentValidatorList;
    private final List<dev.practice.open.infrastructure.open.payment.PaymentApiCaller> paymentApiCallerList;

    @Override
    public void pay(Open open, OpenCommand.PaymentRequest paymentRequest) {
        paymentValidatorList.forEach(paymentValidator -> paymentValidator.validate(open, paymentRequest));
        dev.practice.open.infrastructure.open.payment.PaymentApiCaller payApiCaller = routingApiCaller(paymentRequest);
        payApiCaller.pay(paymentRequest);
    }

    private PaymentApiCaller routingApiCaller(OpenCommand.PaymentRequest request) {
        return paymentApiCallerList.stream()
                .filter(paymentApiCaller -> paymentApiCaller.support(request.getPayMethod()))
                .findFirst()
                .orElseThrow(InvalidParamException::new);
    }
}
