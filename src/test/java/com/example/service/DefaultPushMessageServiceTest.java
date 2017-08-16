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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPushMessageServiceTest {
    private DefaultPushMessageService defaultPushMessageService;

    @Mock
    private PushWrapper pushWrapper;

    @Mock
    private SimpleWeatherForecastService simpleWeatherForecastService;

    @Before
    public void setUp() throws Exception {
        defaultPushMessageService = new DefaultPushMessageService(
                pushWrapper,
                simpleWeatherForecastService
        );
    }

    @Test
    public void test_pushText_callsDependencies() throws Exception {
        when(simpleWeatherForecastService.getWeatherForecast()).thenReturn("test forecast");


        defaultPushMessageService.pushText();


        Message message = new TextMessage("test forecast");
        PushMessage pushMessage = new PushMessage("ABC", message);
        verify(pushWrapper, times(1)).push(pushMessage);
        verify(simpleWeatherForecastService, times(1)).getWeatherForecast();
    }
}