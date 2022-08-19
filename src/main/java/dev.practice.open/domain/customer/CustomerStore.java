package dev.practice.open.domain.customer;

import dev.practice.open.domain.customer.Customer;

public interface CustomerStore {
    dev.practice.open.domain.customer.Customer store(Customer initCustomer);
}
