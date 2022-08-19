package dev.practice.open.infrastructure.open;

import dev.practice.open.common.exception.EntityNotFoundException;
import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenReader;
import dev.practice.open.infrastructure.open.OpenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenReaderImpl implements OpenReader {

    private final OpenRepository openRepository;

    @Override
    public Open getOpen(String openToken) {
        return openRepository.findByOpenToken(openToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
