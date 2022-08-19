package dev.practice.open.infrastructure.account;


import dev.practice.open.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountToken(String accountToken);
}
