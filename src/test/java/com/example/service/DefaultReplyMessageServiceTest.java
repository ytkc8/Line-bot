package com.example.service;

import com.example.wrapper.ReplyWrapper;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.event.source.UserSource;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultReplyMessageServiceTest {
    private DefaultReplyMessageService defaultTextMessageService;

    @Mock
    private ReplyWrapper replyWrapper;

    @Mock
    private WordChainService wordChainService;

    private MessageEvent<TextMessageContent> event;
    private Source source;

    @Before
    public void setUp() throws Exception {
        defaultTextMessageService = new DefaultReplyMessageService(
                replyWrapper,
                wordChainService
        );

        source = new UserSource("abcde");
    }

    @Test
    public void test_replyText_callsDependencies() throws Exception {
        when(wordChainService.getChainWord(anyString())).thenReturn("test2");
        TextMessageContent textMessageContent = new TextMessageContent("111", "test");
        event = new MessageEvent<>("reply token", source, textMessageContent, null);


        defaultTextMessageService.replyText(event);


        verify(wordChainService, times(1)).getChainWord("test");
        Message message = new TextMessage("test2");
        ReplyMessage replyMessage = new ReplyMessage("reply token", message);
        verify(replyWrapper, times(1)).reply(replyMessage);
    }
}