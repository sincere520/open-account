package dev.practice.open.application.open;

import dev.practice.open.domain.notification.NotificationService;
import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.OpenInfo;
import dev.practice.open.domain.open.OpenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenFacade {
    private final OpenService openService;
    private final NotificationService notificationService;

    public String registerOpen(OpenCommand.RegisterOpen registerOpen) {
        var openToken = openService.registerOpen(registerOpen);
        notificationService.sendKakao("ORDER_COMPLETE", "content");
        return openToken;
    }

    public OpenInfo.Main retrieveOpen(String openToken) {
        return openService.retrieveOpen(openToken);
    }

    public void paymentOpen(OpenCommand.PaymentRequest paymentRequest) {
        openService.paymentOpen(paymentRequest);
        notificationService.sendKakao(null, null);
    }

    public void updateReceiverInfo(String openToken, OpenCommand.UpdateReceiverInfoRequest openCommand) {
        openService.updateReceiverInfo(openToken, openCommand);
        notificationService.sendKakao(null, null);
    }
}
