package dev.practice.order.infrastructure.account.optiongroup;

import dev.practice.order.domain.account.optiongroup.AccountOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOptionGroupRepository extends JpaRepository<AccountOptionGroup, Long> {
}
