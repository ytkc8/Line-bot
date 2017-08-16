package com.example.service;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;

public interface ReplyMessageService {
    void replyText(MessageEvent<TextMessageContent> event);
}
