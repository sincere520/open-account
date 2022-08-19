package dev.practice.open.domain.open.gift;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class GiftPaymentCompleteMessage {
    private final String openToken;
}
