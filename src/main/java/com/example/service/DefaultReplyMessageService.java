package com.example.service;

import com.example.wrapper.ReplyWrapper;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("default")
public class DefaultReplyMessageService implements ReplyMessageService {
    private final ReplyWrapper replyWrapper;

    public DefaultReplyMessageService(
            ReplyWrapper replyWrapper
    ) {
        this.replyWrapper = replyWrapper;
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
        return new ReplyMessage(
                event.getReplyToken(),
                new TextMessage(event.getMessage().getText())
        );
    }

    private void printUserId(MessageEvent<TextMessageContent> event) {
        System.out.println("\n\nevent.getSource().getUserId() = " + event.getSource().getUserId() + "\n\n");
    }
}
