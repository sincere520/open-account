package dev.practice.order.domain.order.remit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class RemitPaymentCompleteMessage {
    private final String orderToken;
}
