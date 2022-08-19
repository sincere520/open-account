package dev.practice.open.infrastructure.account.optiongroup;

import dev.practice.open.domain.account.optiongroup.AccountOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOptionGroupRepository extends JpaRepository<AccountOptionGroup, Long> {
}
