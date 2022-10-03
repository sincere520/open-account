package dev.practice.order.domain.account;

public interface AccountService {
    String registerAccount(AccountCommand.RegisterAccountRequest request, String partnerToken);
    void updateAccount(String accountToken1, String accountToken2, long amount);
    void changeOnSale(String accountToken);
    void changeEndOfSale(String accountToken);
    AccountInfo.Main retrieveAccountInfo(String accountToken);
}
