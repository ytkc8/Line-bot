package com.example.integration;

import com.example.model.OWMResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        properties = {
                "DATABASE_URL: jdbc:mysql://localhost/enget_line_bot",
                "DATABASE_USERNAME: root",
                "DATABASE_PASSWORD:",
                "MAIL_HOSTNAME:",
                "MAIL_USERNAME:",
                "MAIL_PASSWORD:"
        }
)
public class ExternalApiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_restTemplate() throws Exception {
        String host = "api.openweathermap.org";
        String apiPath = "/data/2.5/forecast";
        String cityParam = "q=Tokyo";
        String unitParam = "units=Metric";
        String cntParam = "cnt=1";
        String apiKey = "appid=3a981d4a71950ac6430af06740e589b2";
        String uri = "http://" + host + apiPath + "?" +
                cityParam + "&" + unitParam + "&" + cntParam + "&" + apiKey;
        OWMResponse forObject = restTemplate.getForObject(uri, OWMResponse.class);


        assertThat(forObject, is(notNullValue()));
    }
}
