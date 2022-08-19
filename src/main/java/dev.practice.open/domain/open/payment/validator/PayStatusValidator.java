package dev.practice.open.domain.open.payment.validator;

import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.payment.validator.PaymentValidator;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 3)
@Component
public class PayStatusValidator implements PaymentValidator {

    @Override
    public void validate(Open open, OpenCommand.PaymentRequest paymentRequest) {
        if (open.isAlreadyPaymentComplete()) throw new InvalidParamException("이미 결제완료된 주문입니다");
    }
}
