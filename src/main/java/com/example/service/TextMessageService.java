package com.example.service;

import com.linecorp.bot.model.message.TextMessage;

public interface TextMessageService {
    TextMessage replyText(String text);
}
