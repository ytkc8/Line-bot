package com.example.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        properties = {
                "OPEN_WEATHER_MAP_API_KEY: 3a981d4a71950ac6430af06740e589b2",
                "SERVICE_KEY_WEATHERS: 天気:てんき:テンキ:weather"
        }
)
public class DefaultServiceKeyGetterTest {
    @Autowired
    private ServiceKeyGetter defaultServiceKeyGetter;

    @Test
    public void test_getServiceKey_returnsText_weather() throws Exception {
        String key1 = defaultServiceKeyGetter.getServiceKey("天気");
        String key2 = defaultServiceKeyGetter.getServiceKey("てんき");
        String key3 = defaultServiceKeyGetter.getServiceKey("テンキ");
        String key4 = defaultServiceKeyGetter.getServiceKey("天気教えて");
        String key5 = defaultServiceKeyGetter.getServiceKey("weather");


        assertThat(key1, equalTo("weather"));
        assertThat(key2, equalTo("weather"));
        assertThat(key3, equalTo("weather"));
        assertThat(key4, equalTo("weather"));
        assertThat(key5, equalTo("weather"));
    }

    @Test
    public void test_getServiceKey_returnsText_default() throws Exception {
        String key1 = defaultServiceKeyGetter.getServiceKey("");
        String key2 = defaultServiceKeyGetter.getServiceKey("教えて！天気！");


        assertThat(key1, equalTo("default"));
        assertThat(key2, equalTo("default"));
    }
}