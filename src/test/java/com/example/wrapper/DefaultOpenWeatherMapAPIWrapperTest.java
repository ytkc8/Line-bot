package com.example.wrapper;

import com.example.model.OWMResponse;
import com.example.model.Weather;
import com.example.model.WeatherData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class DefaultOpenWeatherMapAPIWrapperTest {
    private RestTemplate restTemplate;

    private DefaultOpenWeatherMapAPIWrapper defaultOpenWeatherMapAPIWrapper;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        defaultOpenWeatherMapAPIWrapper = new DefaultOpenWeatherMapAPIWrapper(restTemplate);
    }

    @Test
    public void test_getWeatherData() throws Exception {
        mockServer.expect(requestTo("http://api.openweathermap.org/data/2.5/forecast?q=Tokyo&units=Metric&cnt=1&appid=3a981d4a71950ac6430af06740e589b2"))
                .andRespond(withSuccess("{\"list\": [{\"dt\": \"1406106000\", \"weather\": [{\"main\": \"Rain\", \"description\": \"little rain\"}]}]}", MediaType.APPLICATION_JSON_UTF8));


        OWMResponse owmResponse = defaultOpenWeatherMapAPIWrapper.getWeatherData();


        WeatherData weatherData = owmResponse.getList().get(0);
        assertThat(weatherData.getDt(), equalTo("1406106000"));
        Weather weather = weatherData.getWeather().get(0);
        assertThat(weather.getMain(), equalTo("Rain"));
        assertThat(weather.getDescription(), equalTo("little rain"));

        mockServer.verify();
    }
}