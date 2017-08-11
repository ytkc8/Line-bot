package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

public class TestDataPersister {
    public static long insertTestDataIntoWord(
            JdbcTemplate jdbcTemplate,
            String sourceWord,
            String englishWord
    ) {
        SimpleJdbcInsert insert = getSimpleJdbcInsert(jdbcTemplate);
        Map<String, Object> params = getParams(sourceWord, englishWord);
        return insert.executeAndReturnKey(params).longValue();
    }

    private static SimpleJdbcInsert getSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("word")
                .usingColumns(
                        "source_word",
                        "english_word"
                )
                .usingGeneratedKeyColumns("id");
    }

    private static Map<String, Object> getParams(String sourceWord, String englishWord) {
        Map<String, Object> params = new HashMap<>();
        params.put("source_word", sourceWord);
        params.put("english_word", englishWord);

        return params;
    }
}
