package dev.practice.order.infrastructure.customer;

import dev.practice.order.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerToken(String customerToken);
}
