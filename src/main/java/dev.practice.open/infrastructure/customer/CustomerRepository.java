package dev.practice.open.infrastructure.customer;

import dev.practice.open.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerToken(String customerToken);
}
