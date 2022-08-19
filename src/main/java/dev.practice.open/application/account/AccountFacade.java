package dev.practice.open.application.account;

import dev.practice.open.domain.notification.NotificationService;
import dev.practice.open.domain.account.AccountCommand;
import dev.practice.open.domain.account.AccountInfo;
import dev.practice.open.domain.account.AccountService;
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
        var accountToken = accountService.registerAccount(request, partnerToken);
        notificationService.sendEmail(null, null, null);
        return accountToken;
    }

    public void changeOnSaleAccount(String accountToken) {
        accountService.changeOnSale(accountToken);
    }

    public void changeEndOfSaleAccount(String accountToken) {
        accountService.changeEndOfSale(accountToken);
    }

    public AccountInfo.Main retrieveAccountInfo(String accountToken) {
        return accountService.retrieveAccountInfo(accountToken);
    }
}
