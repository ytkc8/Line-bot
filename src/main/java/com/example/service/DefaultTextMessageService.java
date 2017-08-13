package com.example.service;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

@Service
public class DefaultTextMessageService implements TextMessageService {
    private LineMessagingService lineMessagingService;

    public DefaultTextMessageService(LineMessagingService lineMessagingService) {
        this.lineMessagingService = lineMessagingService;
    }

    @Override
    public void replyText(MessageEvent<TextMessageContent> event) {
        ReplyMessage replyMessage = new ReplyMessage(
                event.getReplyToken(),
                new TextMessage(event.getMessage().getText())
        );

        lineMessagingService.replyMessage(replyMessage);
    }
}
