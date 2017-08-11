package com.example.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DefaultMessageRepository implements MessageRepository {
    @Override
    public Optional<String> getEnglish(String japanese) {
        return null;
    }
}
