package dev.practice.order.infrastructure.order;

import dev.practice.order.common.exception.EntityNotFoundException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {

    private final OrderRepository openRepository;

    @Override
    public Order getOrder(String openToken) {
        return openRepository.findByOrderToken(openToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
