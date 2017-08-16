package com.example.service;

import com.example.wrapper.PushWrapper;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;

@Service
public class DefaultPushMessageService implements PushMessageService {
    private PushWrapper pushWrapper;

    public DefaultPushMessageService(PushWrapper pushWrapper) {
        this.pushWrapper = pushWrapper;
    }

    @Override
    public void pushText(MessageEvent<TextMessageContent> event) {

    }
}
