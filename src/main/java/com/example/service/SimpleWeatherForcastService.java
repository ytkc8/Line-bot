package com.example.service;

import com.example.model.OWMResponse;
import com.example.model.Weather;
import com.example.model.WeatherData;
import com.example.wrapper.OpenWeatherMapAPIWrapper;
import org.springframework.stereotype.Service;

@Service
public class SimpleWeatherForcastService implements WeatherForcastService {
    private static final String errorMessage = "Sorry... Can't get weather data";

    private final OpenWeatherMapAPIWrapper openWeatherMapAPIWrapper;

    public SimpleWeatherForcastService(OpenWeatherMapAPIWrapper openWeatherMapAPIWrapper) {
        this.openWeatherMapAPIWrapper = openWeatherMapAPIWrapper;
    }

    @Override
    public String getWeatherForecast() {
        OWMResponse owmResponse = openWeatherMapAPIWrapper.getWeatherData();
        WeatherData weatherData = getWeatherData(owmResponse);
        if (weatherData == null) {
            return errorMessage;
        }

        Weather weather = getWeather(weatherData);
        if (weather == null) {
            return errorMessage;
        }

        if (weather.getMain().equals("Rain")) {
            return "傘持って行った方がいいよ";
        } else {
            return "多分傘はいらない";
        }
    }

    private WeatherData getWeatherData(OWMResponse owmResponse) {
        return owmResponse.getList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    private Weather getWeather(WeatherData weatherData) {
        return weatherData.getWeather()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
