package dev.practice.order.domain.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CustomerCommand {
    private final String customerName;
    private final String phoneNumber;

    public Customer toEntity() {
        return Customer.builder()
                .customerName(customerName)
                .phoneNumber(phoneNumber)
                .build();
    }
}
