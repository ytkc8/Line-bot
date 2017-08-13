package com.example.wrapper;

import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import java.io.IOException;

public interface ReplyWrapper {
    BotApiResponse reply(ReplyMessage replyMessage) throws IOException;
}
