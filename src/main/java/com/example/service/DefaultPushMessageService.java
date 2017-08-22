package com.example.service;

import com.example.wrapper.PushWrapper;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DefaultPushMessageService implements PushMessageService {
    public static final String userId = "U4770190ff20ae9f5f1b5a83cef491c02";
    private final PushWrapper pushWrapper;
    private final SimpleWeatherForecastService simpleWeatherForecastService;

    public DefaultPushMessageService(
            PushWrapper pushWrapper,
            SimpleWeatherForecastService simpleWeatherForecastService
    ) {
        this.pushWrapper = pushWrapper;
        this.simpleWeatherForecastService = simpleWeatherForecastService;
    }

    @Override
    public void pushText() {
        try {
            pushWrapper.push(generatePushMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PushMessage generatePushMessage() {
        return new PushMessage(
                userId,
                new TextMessage(getForecast())
        );
    }

    private String getForecast() {
        return simpleWeatherForecastService.getWeatherForecastSummary();
    }
}
