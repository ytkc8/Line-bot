package com.example.service;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.event.source.UserSource;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTextMessageServiceTest {
    private DefaultTextMessageService defaultTextMessageService;

    @Mock
    private LineMessagingService lineMessagingService;

    @Before
    public void setUp() throws Exception {
        defaultTextMessageService = new DefaultTextMessageService(lineMessagingService);
    }

    @Test
    public void test_replyText_callsDependenciesWithCorrectArguments() throws Exception {
        Source source = new UserSource("abcde");
        TextMessageContent textMessageContent = new TextMessageContent("111", "test");
        MessageEvent<TextMessageContent> event = new MessageEvent<>(
                "reply token", source, textMessageContent, null
        );


        defaultTextMessageService.replyText(event);


        Message message = new TextMessage("test");
        ReplyMessage replyMessage = new ReplyMessage("reply token", message);
        verify(lineMessagingService, times(1)).replyMessage(replyMessage);
    }
}