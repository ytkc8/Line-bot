package com.example.wrapper;

import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import java.io.IOException;

public interface PushWrapper {
    BotApiResponse push(PushMessage pushMessage) throws IOException;
}
