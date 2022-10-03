package dev.practice.order.infrastructure.account;

import dev.practice.order.domain.account.Account;
import dev.practice.order.domain.account.AccountInfo;
import dev.practice.order.domain.account.AccountReader;
import dev.practice.order.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountReaderImpl implements AccountReader {
    private final AccountRepository accountRepository;

    @Override
    public Account getAccountBy(String accountToken) {
        return accountRepository.findByAccountToken(accountToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<AccountInfo.AccountOptionGroupInfo> getAccountOptionSeries(Account account) {
        var accountOptionGroupList = account.getAccountOptionGroupList();
        return accountOptionGroupList.stream()
                .map(accountOptionGroup -> {
                    var accountOptionList = accountOptionGroup.getAccountOptionList();
                    var accountOptionInfoList = accountOptionList.stream()
                            .map(AccountInfo.AccountOptionInfo::new)
                            .collect(Collectors.toList());

                    return new AccountInfo.AccountOptionGroupInfo(accountOptionGroup, accountOptionInfoList);
                }).collect(Collectors.toList());
    }
}
