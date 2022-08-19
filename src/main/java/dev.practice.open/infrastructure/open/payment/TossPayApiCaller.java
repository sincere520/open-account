package dev.practice.open.infrastructure.open.payment;

import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.payment.PayMethod;
import dev.practice.open.infrastructure.open.payment.PaymentApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPayApiCaller implements PaymentApiCaller {

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.TOSS_PAY == payMethod;
    }

    @Override
    public void pay(OpenCommand.PaymentRequest request) {
        // TODO - 구현
    }
}
