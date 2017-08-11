package com.example.repository;

import com.example.TestDatabaseRefresher;
import com.example.TestJdbcTemplate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static com.example.TestDataPersister.insertTestDataIntoWord;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultMessageRepositoryTest {
    private JdbcTemplate jdbcTemplate;
    private DefaultMessageRepository defaultMessageRepository;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = TestJdbcTemplate.setUp();
        TestDatabaseRefresher.truncateAllTablesFromDB(jdbcTemplate);
        defaultMessageRepository = new DefaultMessageRepository(jdbcTemplate);
    }

    @Test
    public void test_getEnglish_returnsString() throws Exception {
        insertTestDataIntoWord(jdbcTemplate, "いぬ", "dog");
        insertTestDataIntoWord(jdbcTemplate, "god", "神");



        Optional<String> returnValue1 = defaultMessageRepository.getEnglish("いぬ");
        Optional<String> returnValue2 = defaultMessageRepository.getEnglish("god");


        assertThat(returnValue1.get(), equalTo("dog"));
        assertThat(returnValue2.get(), equalTo("神"));
    }

    @Test
    public void test_getEnglish_returnsEmpty() throws Exception {
        insertTestDataIntoWord(jdbcTemplate, "dog", "dog");


        Optional<String> returnValue = defaultMessageRepository.getEnglish("cat");


        assertThat(returnValue.isPresent(), is(false));
    }
}