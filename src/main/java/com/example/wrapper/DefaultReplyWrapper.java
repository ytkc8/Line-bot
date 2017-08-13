package com.example.wrapper;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import java.io.IOException;

public class DefaultReplyWrapper implements ReplyWrapper {
    private LineMessagingService lineMessagingService;

    public DefaultReplyWrapper(LineMessagingService lineMessagingService) {
        this.lineMessagingService = lineMessagingService;
    }

    @Override
    public BotApiResponse reply(ReplyMessage replyMessage) throws IOException {
        return lineMessagingService.replyMessage(replyMessage).execute().body();
    }
}
