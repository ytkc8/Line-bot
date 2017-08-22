package com.example.eventHandler;

import com.example.service.ReplyMessageService;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageEventHandlerTest {
    private MessageEventHandler messageEventHandler;

    @Mock
    private Map<String, ReplyMessageService> replyMessageServiceMap;

    @Mock
    private ReplyMessageService replyMessageService;

    @Before
    public void setUp() throws Exception {
        messageEventHandler = new MessageEventHandler(replyMessageServiceMap);
    }

    @Test
    public void test_handleTextMessageEvent_callsDependeciesWithCorrectArguments() throws Exception {
        when(replyMessageServiceMap.get(anyString())).thenReturn(replyMessageService);
        MessageEvent<TextMessageContent> event = new MessageEvent<>(
                "", null, new TextMessageContent("", "test"), null
        );


        messageEventHandler.handleTextMessageEvent(event);


        verify(replyMessageService, times(1)).replyText(event);
    }
}