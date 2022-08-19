package dev.practice.open.domain.open.payment;

import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;

public interface PaymentProcessor {
    void pay(Open open, OpenCommand.PaymentRequest request);
}