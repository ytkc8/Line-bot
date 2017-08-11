package com.example;

import org.springframework.jdbc.core.JdbcTemplate;

public class TestDatabaseRefresher {
    private static String truncateWordString = "DELETE FROM word";

    public static void truncateAllTablesFromDB(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.batchUpdate(
                truncateWordString
        );
    }
}
