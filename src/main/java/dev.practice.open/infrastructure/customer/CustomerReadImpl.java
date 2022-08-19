package dev.practice.open.infrastructure.customer;

import dev.practice.open.common.exception.EntityNotFoundException;
import dev.practice.open.domain.customer.Customer;
import dev.practice.open.domain.customer.CustomerReader;
import dev.practice.open.infrastructure.customer.CustomerRepository;
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
