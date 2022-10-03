package dev.practice.order.infrastructure.account;

import dev.practice.order.domain.account.Account;
import dev.practice.order.domain.account.AccountCommand;
import dev.practice.order.domain.account.AccountOptionSeriesFactory;
import dev.practice.order.domain.account.option.AccountOptionStore;
import dev.practice.order.domain.account.optiongroup.AccountOptionGroup;
import dev.practice.order.domain.account.optiongroup.AccountOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountOptionSeriesFactoryImpl implements AccountOptionSeriesFactory {
    private final AccountOptionGroupStore accountOptionGroupStore;
    private final AccountOptionStore accountOptionStore;

    @Override
    public List<AccountOptionGroup> store(AccountCommand.RegisterAccountRequest command, Account account) {
        var accountOptionGroupRequestList = command.getAccountOptionGroupRequestList();
        if (CollectionUtils.isEmpty(accountOptionGroupRequestList)) return Collections.emptyList();

        return accountOptionGroupRequestList.stream()
                .map(requestAccountOptionGroup -> {
                    // accountOptionGroup store
                    var initAccountOptionGroup = requestAccountOptionGroup.toEntity(account);
                    var accountOptionGroup = accountOptionGroupStore.store(initAccountOptionGroup);

                    // accountOption store
                    requestAccountOptionGroup.getAccountOptionRequestList().forEach(requestAccountOption -> {
                        var initAccountOption = requestAccountOption.toEntity(accountOptionGroup);
                        accountOptionStore.store(initAccountOption);
                    });

                    return accountOptionGroup;
                }).collect(Collectors.toList());
    }
}
