package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.account.OrderAccountOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAccountOptionGroupRepository extends JpaRepository<OrderAccountOptionGroup, Long> {
}
