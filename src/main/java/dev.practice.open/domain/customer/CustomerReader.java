package dev.practice.open.domain.customer;

import dev.practice.open.domain.customer.Customer;

public interface CustomerReader {
    dev.practice.open.domain.customer.Customer getCustomer(Long customerId);
    Customer getCustomer(String customerToken);
}
