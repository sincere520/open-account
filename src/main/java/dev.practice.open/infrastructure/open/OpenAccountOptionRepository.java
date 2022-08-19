package dev.practice.open.infrastructure.open;

import dev.practice.open.domain.open.account.OpenAccountOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenAccountOptionRepository extends JpaRepository<OpenAccountOption, Long> {
}
