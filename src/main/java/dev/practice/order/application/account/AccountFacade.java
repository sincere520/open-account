package dev.practice.order.application.account;

import dev.practice.order.domain.account.AccountCommand;
import dev.practice.order.domain.account.AccountInfo;
import dev.practice.order.domain.account.AccountService;
import dev.practice.order.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountFacade {
    private final AccountService accountService;
    private final NotificationService notificationService;

    public String registerAccount(AccountCommand.RegisterAccountRequest request, String partnerToken) {
        log.info("===AccountFacade.registerAccount===");
        var accountToken = accountService.registerAccount(request, partnerToken);
        notificationService.sendEmail(null, null, null);
        return accountToken;
    }

    public void changeOnSaleAccount(String accountToken) {
        log.info("===AccountFacade.changeOnSaleAccount===");
        accountService.changeOnSale(accountToken);
    }

    public void changeEndOfSaleAccount(String accountToken) {
        log.info("===AccountFacade.changeEndOfSaleAccount===");
        accountService.changeEndOfSale(accountToken);
    }

    public AccountInfo.Main retrieveAccountInfo(String accountToken) {
        log.info("===AccountFacade.retrieveAccountInfo===");
        return accountService.retrieveAccountInfo(accountToken);
    }
}
