package com.example.wrapper;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DefaultPushWrapper implements PushWrapper {
    private final LineMessagingService lineMessagingService;

    public DefaultPushWrapper(LineMessagingService lineMessagingService) {
        this.lineMessagingService = lineMessagingService;
    }

    @Override
    public BotApiResponse push(PushMessage pushMessage) throws IOException {
        return lineMessagingService.pushMessage(pushMessage).execute().body();
    }
}
