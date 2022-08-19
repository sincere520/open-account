package dev.practice.open.domain.open.payment.validator;

import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.payment.validator.PaymentValidator;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 2)
@Component
public class PayMethodValidator implements PaymentValidator {

    @Override
    public void validate(Open open, OpenCommand.PaymentRequest paymentRequest) {
        if (!open.getPayMethod().equals(paymentRequest.getPayMethod().name())) {
            throw new InvalidParamException("주문 과정에서의 결제수단이 다릅니다.");
        }
    }
}
