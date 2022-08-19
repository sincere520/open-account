package dev.practice.open.domain.open;

import dev.practice.open.domain.open.account.OpenAccount;

import java.util.List;

public interface OpenAccountSeriesFactory {
    List<OpenAccount> store(Open open, OpenCommand.RegisterOpen requestOpen);
}
