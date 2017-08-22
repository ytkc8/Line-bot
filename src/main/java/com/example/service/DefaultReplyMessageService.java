package com.example.service;

import com.example.wrapper.ReplyWrapper;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("default")
@Primary
public class DefaultReplyMessageService implements ReplyMessageService {
    private final ReplyWrapper replyWrapper;
    private final SimpleWeatherForecastService simpleWeatherForecastService;

    public DefaultReplyMessageService(
            ReplyWrapper replyWrapper,
            SimpleWeatherForecastService simpleWeatherForecastService
    ) {
        this.replyWrapper = replyWrapper;
        this.simpleWeatherForecastService = simpleWeatherForecastService;
    }

    @Override
    public void replyText(MessageEvent<TextMessageContent> event) {
        printUserId(event);
        try {
            replyWrapper.reply(getReplyMessage(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ReplyMessage getReplyMessage(MessageEvent<TextMessageContent> event) {
        String replyText = getReplyText(event.getMessage().getText());

        List<Message> messages = new ArrayList<>();
        messages.add(new TextMessage(replyText));

        return new ReplyMessage(
                event.getReplyToken(),
                messages
        );
    }

    private String getReplyText(String requestText) {
        if (requestText.equals("天気")) {
            return simpleWeatherForecastService.getWeatherForecastSummary();
        }

        return requestText;
    }

    private void printUserId(MessageEvent<TextMessageContent> event) {
        System.out.println("\n\nevent.getSource().getUserId() = " + event.getSource().getUserId() + "\n\n");
    }
}
