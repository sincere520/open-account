package dev.practice.open.domain.open;

import dev.practice.open.domain.open.account.OpenAccount;
import dev.practice.open.domain.open.account.OpenAccountOption;
import dev.practice.open.domain.open.account.OpenAccountOptionGroup;

public interface OpenStore {
    dev.practice.open.domain.open.Open store(Open open);
    OpenAccount store(OpenAccount openAccount);
    OpenAccountOptionGroup store(OpenAccountOptionGroup openAccountOptionGroup);
    OpenAccountOption store(OpenAccountOption openAccountOption);
}
