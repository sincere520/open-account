package dev.practice.order.application.order.remit;

import dev.practice.order.domain.account.AccountService;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.remit.RemitOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemitFacade {
    private final RemitOrderService remitOrderService;
    private final AccountService accountService;

    public void paymentOrder(OrderCommand.PaymentRequest request) {
        log.info("===RemitFacade.paymentOrder===");
        accountService.updateAccount(request.getAccountToken1(),request.getAccountToken2(), request.getAmount());

        remitOrderService.paymentOrder(request);
    }
}
