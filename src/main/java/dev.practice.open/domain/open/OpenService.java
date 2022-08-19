package dev.practice.open.domain.open;

import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.OpenInfo;

public interface OpenService {
    String registerOpen(dev.practice.open.domain.open.OpenCommand.RegisterOpen registerOpen);

    void paymentOpen(dev.practice.open.domain.open.OpenCommand.PaymentRequest paymentRequest);

    OpenInfo.Main retrieveOpen(String openToken);

    void updateReceiverInfo(String openToken, OpenCommand.UpdateReceiverInfoRequest request);
}