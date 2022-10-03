package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.account.OrderAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAccountRepository extends JpaRepository<OrderAccount, Long> {
}
