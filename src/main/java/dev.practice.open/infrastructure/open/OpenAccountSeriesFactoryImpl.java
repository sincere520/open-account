package dev.practice.open.infrastructure.open;

import dev.practice.open.domain.account.AccountReader;
import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.OpenAccountSeriesFactory;
import dev.practice.open.domain.open.OpenStore;
import dev.practice.open.domain.open.account.OpenAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OpenAccountSeriesFactoryImpl implements OpenAccountSeriesFactory {
    private final AccountReader accountReader;
    private final OpenStore openStore;

    @Override
    public List<OpenAccount> store(Open open, OpenCommand.RegisterOpen requestOpen) {
        return requestOpen.getOpenAccountList().stream()
                .map(openAccountRequest -> {
                    var account = accountReader.getAccountBy(openAccountRequest.getAccountToken());
                    var initOpenAccount = openAccountRequest.toEntity(open, account);
                    var openAccount = openStore.store(initOpenAccount);

                    openAccountRequest.getOpenAccountOptionGroupList().forEach(openAccountOptionGroupRequest -> {
                        var initOpenAccountOptionGroup = openAccountOptionGroupRequest.toEntity(openAccount);
                        var openAccountOptionGroup = openStore.store(initOpenAccountOptionGroup);

                        openAccountOptionGroupRequest.getOpenAccountOptionList().forEach(openAccountOptionRequest -> {
                            var initOpenAccountOption = openAccountOptionRequest.toEntity(openAccountOptionGroup);
                            openStore.store(initOpenAccountOption);
                        });
                    });
                    return openAccount;
                }).collect(Collectors.toList());
    }
}
