package dev.practice.order.infrastructure.account.option;


import dev.practice.order.domain.account.option.AccountOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOptionRepository extends JpaRepository<AccountOption, Long> {
}
