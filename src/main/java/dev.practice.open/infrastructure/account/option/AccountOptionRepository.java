package dev.practice.open.infrastructure.account.option;


import dev.practice.open.domain.account.option.AccountOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOptionRepository extends JpaRepository<AccountOption, Long> {
}
