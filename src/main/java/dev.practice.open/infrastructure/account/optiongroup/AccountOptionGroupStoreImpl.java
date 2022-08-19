package dev.practice.open.infrastructure.account.optiongroup;

import dev.practice.open.domain.account.optiongroup.AccountOptionGroup;
import dev.practice.open.domain.account.optiongroup.AccountOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountOptionGroupStoreImpl implements AccountOptionGroupStore {

    private final AccountOptionGroupRepository accountOptionGroupRepository;

    @Override
    public AccountOptionGroup store(AccountOptionGroup accountOptionGroup) {
        return accountOptionGroupRepository.save(accountOptionGroup);
    }
}
