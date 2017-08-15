package com.example.service;

import com.example.wrapper.ReplyWrapper;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DefaultTextMessageService implements TextMessageService {
    private final ReplyWrapper replyWrapper;
    private final SimpleWeatherForecastService simpleWeatherForecastService;

    public DefaultTextMessageService(
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
        return new ReplyMessage(
                event.getReplyToken(),
                new TextMessage(getReplyText(event.getMessage().getText()))
        );
    }

    private String getReplyText(String requestText) {
        if (requestText.equals("天気")) {
            return simpleWeatherForecastService.getWeatherForecast();
        }

        return requestText;
    }
}
