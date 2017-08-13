package com.example.eventHandler;

import com.example.service.TextMessageService;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageEventHandlerTest {
    private MessageEventHandler messageEventHandler;

    @Mock
    private TextMessageService textMessageService;

    @Before
    public void setUp() throws Exception {
        messageEventHandler = new MessageEventHandler(textMessageService);
    }

    @Test
    public void test_handleTextMessageEvent_callsDependeciesWithCorrectArguments() throws Exception {
        MessageEvent<TextMessageContent> event = new MessageEvent<>(
                "", null, new TextMessageContent("", "test"), null
        );


        messageEventHandler.handleTextMessageEvent(event);


        verify(textMessageService, times(1)).replyText(event);
    }
}