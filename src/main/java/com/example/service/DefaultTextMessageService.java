package com.example.service;

import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

@Service
public class DefaultTextMessageService implements TextMessageService {
    @Override
    public TextMessage replyText(String text) {
        return null;
    }
}
