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

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherReplyMessageServiceTest {
    private WeatherReplyMessageService weatherReplyMessageService;

    @Mock
    private ReplyWrapper replyWrapper;

    @Mock
    private SimpleWeatherForecastService simpleWeatherForecastService;

    private MessageEvent<TextMessageContent> event;
    private Source source;

    @Before
    public void setUp() throws Exception {
        weatherReplyMessageService = new WeatherReplyMessageService(replyWrapper, simpleWeatherForecastService);

        source = new UserSource("abcde");
    }

    @Test
    public void test_replyText_callsDependencies_whenTextIsWeather() throws Exception {
        when(simpleWeatherForecastService.getWeatherForecastSummary()).thenReturn("forecast summary message");
        when(simpleWeatherForecastService.getWeatherForecast()).thenReturn("forecast detail message");
        TextMessageContent textMessageContent = new TextMessageContent("111", "天気");
        event = new MessageEvent<>("reply token", source, textMessageContent, null);


        weatherReplyMessageService.replyText(event);


        Message summaryMessage = new TextMessage("forecast summary message");
        Message detailMessage = new TextMessage("forecast detail message");
        List<Message> messages = asList(summaryMessage, detailMessage);
        ReplyMessage replyMessage = new ReplyMessage("reply token", messages);
        verify(replyWrapper, times(1)).reply(replyMessage);
        verify(simpleWeatherForecastService, times(1)).getWeatherForecastSummary();
        verify(simpleWeatherForecastService, times(1)).getWeatherForecast();
    }
}