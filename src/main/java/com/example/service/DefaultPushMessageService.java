package com.example.service;

import com.example.wrapper.PushWrapper;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DefaultPushMessageService implements PushMessageService {
    private PushWrapper pushWrapper;
    private SimpleWeatherForecastService simpleWeatherForecastService;

    public DefaultPushMessageService(
            PushWrapper pushWrapper,
            SimpleWeatherForecastService simpleWeatherForecastService
    ) {
        this.pushWrapper = pushWrapper;
        this.simpleWeatherForecastService = simpleWeatherForecastService;
    }

    @Override
    public void pushText() {
        System.out.println("Hello!!!");
        try {
            pushWrapper.push(generatePushMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PushMessage generatePushMessage() {
        return new PushMessage(
                "ABC",
                new TextMessage(getForecast())
        );
    }

    private String getForecast() {
        return simpleWeatherForecastService.getWeatherForecast();
    }
}
