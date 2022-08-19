package dev.practice.open.infrastructure.open.payment;

import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.payment.PayMethod;

public interface PaymentApiCaller {
    boolean support(PayMethod payMethod);
    void pay(OpenCommand.PaymentRequest request);
}
