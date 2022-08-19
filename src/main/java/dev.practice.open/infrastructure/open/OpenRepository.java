package dev.practice.open.infrastructure.open;

import dev.practice.open.domain.open.Open;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpenRepository extends JpaRepository<Open, Long> {
    Optional<Open> findByOpenToken(String openToken);
}
