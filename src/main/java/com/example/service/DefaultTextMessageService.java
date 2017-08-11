package com.example.service;

import com.example.repository.MessageRepository;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.stereotype.Service;

@Service
public class DefaultTextMessageService implements TextMessageService {
    private MessageRepository messageRepository;

    public DefaultTextMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public TextMessage replyText(String text) {
        return new TextMessage(messageRepository.getEnglish(text).orElse(text));
    }
}
