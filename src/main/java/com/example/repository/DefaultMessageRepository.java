package com.example.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DefaultMessageRepository implements MessageRepository {
    private JdbcTemplate jdbcTemplate;

    public DefaultMessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<String> getEnglish(String japanese) {
        String queryString = "SELECT english_word FROM word WHERE source_word = ?";

        try {
            return Optional.of(
                    jdbcTemplate.queryForObject(
                        queryString,
                        (rs, i) -> rs.getString("english_word"),
                        japanese
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
