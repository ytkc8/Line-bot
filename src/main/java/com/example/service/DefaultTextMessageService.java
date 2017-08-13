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
    private ReplyWrapper replyWrapper;

    public DefaultTextMessageService(ReplyWrapper replyWrapper) {
        this.replyWrapper = replyWrapper;
    }

    @Override
    public void replyText(MessageEvent<TextMessageContent> event) {
        ReplyMessage replyMessage = new ReplyMessage(
                event.getReplyToken(),
                new TextMessage(event.getMessage().getText())
        );

        try {
            replyWrapper.reply(replyMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
