package dev.practice.open.domain.open;

import dev.practice.open.domain.open.Open;

public interface OpenReader {
    Open getOpen(String openToken);
}
