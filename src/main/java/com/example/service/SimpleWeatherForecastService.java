package com.example.service;

import com.example.model.OpenWeatherMapResponse;
import com.example.model.Weather;
import com.example.model.WeatherData;
import com.example.wrapper.OpenWeatherMapAPIWrapper;
import org.springframework.stereotype.Service;

@Service
public class SimpleWeatherForecastService implements WeatherForcastService {
    private static final String errorMessage = "Sorry... Can't get weather data.";

    private final OpenWeatherMapAPIWrapper openWeatherMapAPIWrapper;

    public SimpleWeatherForecastService(OpenWeatherMapAPIWrapper openWeatherMapAPIWrapper) {
        this.openWeatherMapAPIWrapper = openWeatherMapAPIWrapper;
    }

    @Override
    public String getWeatherForecast() {
        OpenWeatherMapResponse openWeatherMapResponse = openWeatherMapAPIWrapper.get();
        WeatherData weatherData = getWeatherData(openWeatherMapResponse);
        if (weatherData == null) {
            return errorMessage + " error code: 001";
        }

        Weather weather = getWeather(weatherData);
        if (weather == null) {
            return errorMessage + " error code: 002";
        }

        String weatherText = weather.getMain();
        if (weatherText == null) {
            return errorMessage + " error code: 003";
        }

        if (weatherText.equals("Rain")) {
            return "傘持って行った方がいいよ";
        } else {
            return "多分傘はいらない";
        }
    }

    private WeatherData getWeatherData(OpenWeatherMapResponse openWeatherMapResponse) {
        return openWeatherMapResponse.getList()
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
