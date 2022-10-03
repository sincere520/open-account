package dev.practice.order.infrastructure.customer;

import dev.practice.order.domain.customer.Customer;
import dev.practice.order.domain.customer.CustomerReader;
import dev.practice.order.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerReadImpl implements CustomerReader {
    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Customer getCustomer(String customerToken) {
        return customerRepository.findByCustomerToken(customerToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
