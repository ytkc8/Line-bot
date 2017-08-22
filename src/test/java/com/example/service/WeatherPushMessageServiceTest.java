package com.example.service;

import com.example.wrapper.PushWrapper;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherPushMessageServiceTest {
    private WeatherPushMessageService weatherPushMessageService;

    @Mock
    private PushWrapper pushWrapper;

    @Mock
    private SimpleWeatherForecastService simpleWeatherForecastService;

    @Before
    public void setUp() throws Exception {
        weatherPushMessageService = new WeatherPushMessageService(
                pushWrapper,
                simpleWeatherForecastService
        );
    }

    @Test
    public void test_pushText_callsDependencies() throws Exception {
        when(simpleWeatherForecastService.getWeatherForecastSummary()).thenReturn("test summary forecast");
        when(simpleWeatherForecastService.getWeatherForecast()).thenReturn("test detail forecast");


        weatherPushMessageService.pushText();


        Message summaryMessage = new TextMessage("test summary forecast");
        Message detailMessage = new TextMessage("test detail forecast");
        List<Message> messages = asList(summaryMessage, detailMessage);
        PushMessage pushMessage = new PushMessage(
                "U4770190ff20ae9f5f1b5a83cef491c02",
                messages
        );
        verify(pushWrapper, times(1)).push(pushMessage);
        verify(simpleWeatherForecastService, times(1)).getWeatherForecastSummary();
        verify(simpleWeatherForecastService, times(1)).getWeatherForecast();
    }
}