package com.example.wrapper;

import com.example.helper.APIUriGetter;
import com.example.model.OpenWeatherMapResponse;
import com.example.model.Weather;
import com.example.model.WeatherData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOpenWeatherMapAPIWrapperTest {
    @Mock
    private APIUriGetter APIUriGetter;

    private RestTemplate restTemplate;

    private DefaultOpenWeatherMapAPIWrapper defaultOpenWeatherMapAPIWrapper;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        defaultOpenWeatherMapAPIWrapper = new DefaultOpenWeatherMapAPIWrapper(
                restTemplate, APIUriGetter
        );
    }

    @Test
    public void test_getWeatherData() throws Exception {
        String uri = "http://api.openweathermap.org/data/2.5/forecast?q=Tokyo&units=Metric&cnt=1&appid=3a981d4a71950ac6430af06740e589b2";
        when(APIUriGetter.getUri()).thenReturn(uri);
        mockServer.expect(requestTo(uri))
                .andRespond(withSuccess("{\"list\": [{\"dt\": \"1406106000\", \"weather\": [{\"main\": \"Rain\", \"description\": \"little rain\"}]}]}", MediaType.APPLICATION_JSON_UTF8));


        OpenWeatherMapResponse openWeatherMapResponse = defaultOpenWeatherMapAPIWrapper.getWeatherData();


        WeatherData weatherData = openWeatherMapResponse.getList().get(0);
        assertThat(weatherData.getDt(), equalTo("1406106000"));
        Weather weather = weatherData.getWeather().get(0);
        assertThat(weather.getMain(), equalTo("Rain"));
        assertThat(weather.getDescription(), equalTo("little rain"));

        mockServer.verify();

        verify(APIUriGetter, times(1)).getUri();
    }
}