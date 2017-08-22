package com.example.service;

import com.example.wrapper.PushWrapper;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherPushMessageService implements PushMessageService {
    private static final String userId = "U4770190ff20ae9f5f1b5a83cef491c02";
    private final PushWrapper pushWrapper;
    private final SimpleWeatherForecastService simpleWeatherForecastService;

    public WeatherPushMessageService(
            PushWrapper pushWrapper,
            SimpleWeatherForecastService simpleWeatherForecastService
    ) {
        this.pushWrapper = pushWrapper;
        this.simpleWeatherForecastService = simpleWeatherForecastService;
    }

    @Override
    public void pushText() {
        try {
            pushWrapper.push(getPushMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PushMessage getPushMessage() {
        String weatherForecastSummary = simpleWeatherForecastService.getWeatherForecastSummary();
        String weatherForecast = simpleWeatherForecastService.getWeatherForecast();

        List<Message> messages = new ArrayList<>();
        messages.add(new TextMessage(weatherForecastSummary));
        messages.add(new TextMessage(weatherForecast));

        return new PushMessage(
                userId,
                messages
        );
    }
}
