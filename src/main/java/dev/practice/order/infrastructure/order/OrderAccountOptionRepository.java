package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.account.OrderAccountOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAccountOptionRepository extends JpaRepository<OrderAccountOption, Long> {
}
