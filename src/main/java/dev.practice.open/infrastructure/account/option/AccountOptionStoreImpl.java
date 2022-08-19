package dev.practice.open.infrastructure.account.option;

import dev.practice.open.domain.account.option.AccountOption;
import dev.practice.open.domain.account.option.AccountOptionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountOptionStoreImpl implements AccountOptionStore {

    private final AccountOptionRepository accountOptionRepository;

    @Override
    public void store(AccountOption accountOption) {
        accountOptionRepository.save(accountOption);
    }
}
