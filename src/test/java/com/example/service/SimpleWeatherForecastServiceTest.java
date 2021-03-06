package com.example.service;

import com.example.model.OpenWeatherMapResponse;
import com.example.model.Weather;
import com.example.model.WeatherData;
import com.example.wrapper.OpenWeatherMapAPIWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleWeatherForecastServiceTest {
    private SimpleWeatherForecastService simpleWeatherForecastService;
    
    @Mock
    private OpenWeatherMapAPIWrapper openWeatherMapAPIWrapper;
    
    @Before
    public void setUp() throws Exception {
        simpleWeatherForecastService = new SimpleWeatherForecastService(openWeatherMapAPIWrapper);
    }

    @Test
    public void test_getWeatherForecast_callsDependencies() throws Exception {
        Weather weather = new Weather("", "");
        WeatherData weatherData = new WeatherData("", singletonList(weather));
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        simpleWeatherForecastService.getWeatherForecast();


        verify(openWeatherMapAPIWrapper, times(1)).get();
    }

    @Test
    public void test_getWeatherForecast_returnsWeatherForecast_onSuccess() throws Exception {
        Weather weather = new Weather("Cloud", "some cloud.");
        WeatherData weatherData = new WeatherData("", singletonList(weather));
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        String weatherForecast = simpleWeatherForecastService.getWeatherForecast();


        String expectedText =
                "Main: Cloud" +
                "\n\n" +
                "Description: some cloud.";
        assertThat(weatherForecast, equalTo(expectedText));
    }

    @Test
    public void test_getWeatherForecast_returnsWeatherForecast_onFailure() throws Exception {
        Weather weather = new Weather(null, "some cloud.");
        WeatherData weatherData = new WeatherData("", singletonList(weather));
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        String weatherForecast = simpleWeatherForecastService.getWeatherForecast();


        assertThat(weatherForecast, equalTo("Sorry... Can't get weather data."));
    }

    @Test
    public void test_getWeatherForecastSummary_callsDependencies() throws Exception {
        Weather weather = new Weather("", "");
        WeatherData weatherData = new WeatherData("", singletonList(weather));
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        simpleWeatherForecastService.getWeatherForecastSummary();


        verify(openWeatherMapAPIWrapper, times(1)).get();
    }

    @Test
    public void test_getWeatherForecastSummary_returnsForecast_whenWeatherIsRain() throws Exception {
        Weather rain = new Weather("Rain", "little rain");
        WeatherData weatherData = new WeatherData("11111111", singletonList(rain));
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        String returnValue = simpleWeatherForecastService.getWeatherForecastSummary();


        assertThat(returnValue, equalTo("傘持って行った方がいいよ"));
    }

    @Test
    public void test_getWeatherForecastSummary_returnsForecast_whenWeatherIsNotRain() throws Exception {
        Weather rain = new Weather("Cloud", "some cloud");
        WeatherData weatherData = new WeatherData("11111111", singletonList(rain));
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        String returnValue = simpleWeatherForecastService.getWeatherForecastSummary();


        assertThat(returnValue, equalTo("多分傘はいらない"));
    }

    @Test
    public void test_getWeatherForecastSummary_returnsForecast_whenThereIsNoWeatherData() throws Exception {
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(emptyList());
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        String returnValue = simpleWeatherForecastService.getWeatherForecastSummary();


        assertThat(returnValue, equalTo("Sorry... Can't get weather data."));
    }

    @Test
    public void test_getWeatherForecastSummary_returnsForecast_whenThereIsNoWeather() throws Exception {
        WeatherData weatherData = new WeatherData("11111111", emptyList());
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        String returnValue = simpleWeatherForecastService.getWeatherForecastSummary();


        assertThat(returnValue, equalTo("Sorry... Can't get weather data."));
    }

    @Test
    public void test_getWeatherForecastSummary_returnsForecast_whenThereIsNoMain() throws Exception {
        Weather mainNull = new Weather(null, "some cloud");
        WeatherData weatherData = new WeatherData("11111111", singletonList(mainNull));
        OpenWeatherMapResponse openWeatherMapResponse = new OpenWeatherMapResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.get()).thenReturn(openWeatherMapResponse);


        String returnValue = simpleWeatherForecastService.getWeatherForecastSummary();


        assertThat(returnValue, equalTo("Sorry... Can't get weather data."));
    }
}