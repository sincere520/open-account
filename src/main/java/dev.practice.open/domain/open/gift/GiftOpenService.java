package dev.practice.open.domain.open.gift;

import dev.practice.open.domain.open.OpenCommand;

public interface GiftOpenService {
    void paymentOpen(OpenCommand.PaymentRequest paymentRequest);
}
