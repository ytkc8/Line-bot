package com.example.service;

import com.example.model.OWMResponse;
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
public class SimpleWeatherForcastServiceTest {
    private SimpleWeatherForcastService simpleWeatherForcastService;
    
    @Mock
    private OpenWeatherMapAPIWrapper openWeatherMapAPIWrapper;
    
    @Before
    public void setUp() throws Exception {
        simpleWeatherForcastService = new SimpleWeatherForcastService(openWeatherMapAPIWrapper);
    }

    @Test
    public void test_getWeatherForecast_callsDependencies() throws Exception {
        Weather weather = new Weather("", "");
        WeatherData weatherData = new WeatherData("", singletonList(weather));
        OWMResponse owmResponse = new OWMResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.getWeatherData()).thenReturn(owmResponse);


        simpleWeatherForcastService.getWeatherForecast();


        verify(openWeatherMapAPIWrapper, times(1)).getWeatherData();
    }

    @Test
    public void test_getWeatherForecast_returnsForecast_whenWeatherIsRain() throws Exception {
        Weather rain = new Weather("Rain", "little rain");
        WeatherData weatherData = new WeatherData("11111111", singletonList(rain));
        OWMResponse owmResponse = new OWMResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.getWeatherData()).thenReturn(owmResponse);


        String returnValue = simpleWeatherForcastService.getWeatherForecast();


        assertThat(returnValue, equalTo("傘持って行った方がいいよ"));
    }

    @Test
    public void test_getWeatherForecast_returnsForecast_whenWeatherIsNotRain() throws Exception {
        Weather rain = new Weather("Cloud", "some cloud");
        WeatherData weatherData = new WeatherData("11111111", singletonList(rain));
        OWMResponse owmResponse = new OWMResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.getWeatherData()).thenReturn(owmResponse);


        String returnValue = simpleWeatherForcastService.getWeatherForecast();


        assertThat(returnValue, equalTo("多分傘はいらない"));
    }

    @Test
    public void test_getWeatherForecast_returnsForecast_whenThereIsNoWeatherData() throws Exception {
        OWMResponse owmResponse = new OWMResponse(emptyList());
        when(openWeatherMapAPIWrapper.getWeatherData()).thenReturn(owmResponse);


        String returnValue = simpleWeatherForcastService.getWeatherForecast();


        assertThat(returnValue, equalTo("Sorry... Can't get weather data"));
    }

    @Test
    public void test_getWeatherForecast_returnsForecast_whenThereIsNoWeather() throws Exception {
        WeatherData weatherData = new WeatherData("11111111", emptyList());
        OWMResponse owmResponse = new OWMResponse(singletonList(weatherData));
        when(openWeatherMapAPIWrapper.getWeatherData()).thenReturn(owmResponse);


        String returnValue = simpleWeatherForcastService.getWeatherForecast();


        assertThat(returnValue, equalTo("Sorry... Can't get weather data"));
    }
}