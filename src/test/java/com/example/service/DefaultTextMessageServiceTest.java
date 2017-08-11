package com.example.service;

import com.example.repository.MessageRepository;
import com.linecorp.bot.model.message.TextMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTextMessageServiceTest {
    private DefaultTextMessageService defaultTextMessageService;

    @Mock
    private MessageRepository messageRepository;

    @Before
    public void setUp() throws Exception {
        defaultTextMessageService = new DefaultTextMessageService(messageRepository);
    }

    @Test
    public void test_replyText_returnsTextMessage_fromRepoReturnValueString() throws Exception {
        when(messageRepository.getEnglish(anyString())).thenReturn(Optional.of("dog"));


        TextMessage returnValue = defaultTextMessageService.replyText("いぬ");


        verify(messageRepository, times(1)).getEnglish("いぬ");
        assertThat(returnValue.getText(), equalTo("dog"));
    }

    @Test
    public void test_replyText_returnsArgumentText_whenRepoReturnValueIsEmpty() throws Exception {
        when(messageRepository.getEnglish(anyString())).thenReturn(Optional.empty());


        TextMessage returnValue = defaultTextMessageService.replyText("hogehoge");


        verify(messageRepository, times(1)).getEnglish("hogehoge");
        assertThat(returnValue.getText(), equalTo("hogehoge"));
    }
}