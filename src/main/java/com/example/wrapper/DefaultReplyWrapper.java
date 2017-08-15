package com.example.wrapper;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DefaultReplyWrapper implements ReplyWrapper {
    private final LineMessagingService lineMessagingService;

    public DefaultReplyWrapper(LineMessagingService lineMessagingService) {
        this.lineMessagingService = lineMessagingService;
    }

    @Override
    public BotApiResponse reply(ReplyMessage replyMessage) throws IOException {
        return lineMessagingService.replyMessage(replyMessage).execute().body();
    }
}
