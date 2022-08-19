package dev.practice.open.domain.open.payment.validator;

import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;

public interface PaymentValidator {
    void validate(Open open, OpenCommand.PaymentRequest paymentRequest);
}
