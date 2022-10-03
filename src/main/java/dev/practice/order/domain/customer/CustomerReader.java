package dev.practice.order.domain.customer;

public interface CustomerReader {
    Customer getCustomer(Long customerId);
    Customer getCustomer(String customerToken);
}
