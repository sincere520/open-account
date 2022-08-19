package dev.practice.open.infrastructure.open;

import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenStore;
import dev.practice.open.domain.open.account.OpenAccount;
import dev.practice.open.domain.open.account.OpenAccountOption;
import dev.practice.open.domain.open.account.OpenAccountOptionGroup;
import dev.practice.open.infrastructure.open.OpenAccountOptionGroupRepository;
import dev.practice.open.infrastructure.open.OpenAccountOptionRepository;
import dev.practice.open.infrastructure.open.OpenAccountRepository;
import dev.practice.open.infrastructure.open.OpenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenStoreImpl implements OpenStore {
    private final OpenRepository openRepository;
    private final OpenAccountRepository openAccountRepository;
    private final OpenAccountOptionGroupRepository openAccountOptionGroupRepository;
    private final OpenAccountOptionRepository openAccountOptionRepository;

    @Override
    public Open store(Open open) {
        return openRepository.save(open);
    }

    @Override
    public OpenAccount store(OpenAccount openAccount) {
        return openAccountRepository.save(openAccount);
    }

    @Override
    public OpenAccountOptionGroup store(OpenAccountOptionGroup openAccountOptionGroup) {
        return openAccountOptionGroupRepository.save(openAccountOptionGroup);
    }

    @Override
    public OpenAccountOption store(OpenAccountOption openAccountOption) {
        return openAccountOptionRepository.save(openAccountOption);
    }
}
