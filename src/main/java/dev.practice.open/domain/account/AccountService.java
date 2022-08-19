package dev.practice.open.domain.account;

public interface AccountService {
    String registerAccount(AccountCommand.RegisterAccountRequest request, String partnerToken);
    void changeOnSale(String accountToken);
    void changeEndOfSale(String accountToken);
    AccountInfo.Main retrieveAccountInfo(String accountToken);
}
