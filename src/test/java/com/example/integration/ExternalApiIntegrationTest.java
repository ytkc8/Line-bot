package com.example.integration;

import com.example.helper.OpenWeatherMapAPIUriGetter;
import com.example.model.OpenWeatherMapResponse;
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
                "OPEN_WEATHER_MAP_API_KEY: 3a981d4a71950ac6430af06740e589b2",
                "SERVICE_KEY_WEATHERS:"
        }
)
public class ExternalApiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OpenWeatherMapAPIUriGetter defaultOpenWeatherMapAPIUriGetter;

    @Test
    public void test_getUri_and_convertObjectFromApiResponse() throws Exception {
        String uri = defaultOpenWeatherMapAPIUriGetter.getUri();
        OpenWeatherMapResponse forObject = restTemplate.getForObject(uri, OpenWeatherMapResponse.class);


        assertThat(forObject, is(notNullValue()));
        assertThat(forObject.getList(), is(notNullValue()));
    }
}
