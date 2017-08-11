package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

public class TestJdbcTemplate {
    public static JdbcTemplate setUp() {
        String testDbUrl = "jdbc:mysql://localhost/enget_lie_bot_test";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(testDbUrl);
        dataSource.setUsername("root");

        Properties properties = new Properties();
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
        dataSource.setConnectionProperties(properties);

        return new JdbcTemplate(dataSource);
    }
}
