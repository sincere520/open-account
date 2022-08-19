package dev.practice.open.infrastructure.open;

import dev.practice.open.domain.open.account.OpenAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenAccountRepository extends JpaRepository<OpenAccount, Long> {
}
