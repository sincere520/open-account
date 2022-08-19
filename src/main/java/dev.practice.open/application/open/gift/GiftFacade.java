package dev.practice.open.application.open.gift;

import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.gift.GiftOpenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftFacade {
    private final GiftOpenService giftOpenService;

    public void paymentOpen(OpenCommand.PaymentRequest request) {
        giftOpenService.paymentOpen(request);
    }
}
