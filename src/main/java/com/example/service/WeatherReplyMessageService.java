package com.example.service;

import com.example.wrapper.ReplyWrapper;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("weather")
public class WeatherReplyMessageService implements ReplyMessageService {
    private final ReplyWrapper replyWrapper;
    private final SimpleWeatherForecastService simpleWeatherForecastService;

    public WeatherReplyMessageService(
            ReplyWrapper replyWrapper,
            SimpleWeatherForecastService simpleWeatherForecastService
    ) {
        this.replyWrapper = replyWrapper;
        this.simpleWeatherForecastService = simpleWeatherForecastService;
    }

    @Override
    public void replyText(MessageEvent<TextMessageContent> event) {
        try {
            replyWrapper.reply(getReplyMessage(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ReplyMessage getReplyMessage(MessageEvent<TextMessageContent> event) {
        String weatherForecastSummary = simpleWeatherForecastService.getWeatherForecastSummary();
        String weatherForecast = simpleWeatherForecastService.getWeatherForecast();

        List<Message> messages = new ArrayList<>();
        messages.add(new TextMessage(weatherForecastSummary));
        messages.add(new TextMessage(weatherForecast));

        return new ReplyMessage(
                event.getReplyToken(),
                messages
        );
    }
}
