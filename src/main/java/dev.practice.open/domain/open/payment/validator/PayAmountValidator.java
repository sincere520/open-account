package dev.practice.open.domain.open.payment.validator;

import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.payment.validator.PaymentValidator;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 1)
@Component
public class PayAmountValidator implements PaymentValidator {

    @Override
    public void validate(Open open, OpenCommand.PaymentRequest paymentRequest) {
        if (!open.calculateTotalAmount().equals(paymentRequest.getAmount()))
            throw new InvalidParamException("주문가격이 불일치합니다");
    }
}
