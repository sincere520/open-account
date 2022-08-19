package dev.practice.open.domain.customer;

import dev.practice.open.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CustomerCommand {
    private final String customerName;
    private final String phoneNumber;

    public dev.practice.open.domain.customer.Customer toEntity() {
        return Customer.builder()
                .customerName(customerName)
                .phoneNumber(phoneNumber)
                .build();
    }
}
