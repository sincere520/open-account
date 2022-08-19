package dev.practice.open.domain.open.gift;

import dev.practice.open.common.exception.IllegalStatusException;
import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.OpenReader;
import dev.practice.open.domain.open.gift.GiftMessageChannelSender;
import dev.practice.open.domain.open.gift.GiftOpenService;
import dev.practice.open.domain.open.gift.GiftPaymentCompleteMessage;
import dev.practice.open.domain.open.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftOpenServiceImpl implements GiftOpenService {
    private final OpenReader openReader;
    private final PaymentProcessor paymentProcessor;
    private final GiftMessageChannelSender giftMessageChannelSender;

    @Override
    @Transactional
    public void paymentOpen(OpenCommand.PaymentRequest paymentRequest) {
        log.info("GiftOpenService.paymentOpen request = {}", paymentRequest);
        var open = openReader.getOpen(paymentRequest.getOpenToken());

        // 아래 로직을 추가하면, paymentProcessor.pay 실행 이후의 보상 트랜잭션 발생을 막을 수 있다
        if (open.getStatus() != Open.Status.INIT) throw new IllegalStatusException();

        //paymentProcessor.pay(open, paymentRequest);
        //open.openComplete();

        giftMessageChannelSender.paymentComplete(new GiftPaymentCompleteMessage(open.getOpenToken()));
    }
}
