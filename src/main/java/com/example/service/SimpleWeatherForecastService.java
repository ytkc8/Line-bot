package com.example.service;

import com.example.model.OpenWeatherMapResponse;
import com.example.model.Weather;
import com.example.model.WeatherData;
import com.example.wrapper.OpenWeatherMapAPIWrapper;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class SimpleWeatherForecastService implements WeatherForcastService {
    private static final String errorMessage = "Sorry... Can't get weather data.";

    private final OpenWeatherMapAPIWrapper openWeatherMapAPIWrapper;

    public SimpleWeatherForecastService(OpenWeatherMapAPIWrapper openWeatherMapAPIWrapper) {
        this.openWeatherMapAPIWrapper = openWeatherMapAPIWrapper;
    }

    @Override
    public String getWeatherForecast() {
        Weather weather = getWeather(openWeatherMapAPIWrapper.get());
        String main = weather.getMain();
        if (main.equals("empty")) {
            return errorMessage;
        }

        String description = weather.getDescription();

        return "Main: " + main +
                "\n\n" +
                "Description: " + description;
    }

    @Override
    public String getWeatherForecastSummary() {
        Weather weather = getWeather(openWeatherMapAPIWrapper.get());
        String main = weather.getMain();

        switch (main) {
            case "empty":
                return errorMessage;
            case "Rain":
                return "傘持って行った方がいいよ";
            default:
                return "多分傘はいらない";
        }
    }

    private Weather getWeather(OpenWeatherMapResponse openWeatherMapResponse) {
        return getWeather(getWeatherData(openWeatherMapResponse));
    }

    private WeatherData getWeatherData(OpenWeatherMapResponse openWeatherMapResponse) {
        return openWeatherMapResponse.getList()
                .stream()
                .findFirst()
                .orElse(new WeatherData("", emptyList()));
    }

    private Weather getWeather(WeatherData weatherData) {
        return weatherData.getWeather()
                .stream()
                .filter(weather -> weather.getMain() != null)
                .findFirst()
                .orElse(new Weather("empty", ""));
    }
}
