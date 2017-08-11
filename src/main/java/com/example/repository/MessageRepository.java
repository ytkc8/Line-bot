package com.example.repository;

import java.util.Optional;

public interface MessageRepository {
    Optional<String> getEnglish(String japanese);
}
