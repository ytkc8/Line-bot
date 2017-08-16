package com.example.controller;

import com.example.service.PushMessageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PushEventControllerTest {
    private PushEventController pushEventController;
    private MockMvc mockMvc;

    @Mock
    private PushMessageService pushMessageService;

    @Before
    public void setUp() throws Exception {
        pushMessageService = mock(PushMessageService.class);
        pushEventController = new PushEventController(pushMessageService);
        mockMvc = MockMvcBuilders.standaloneSetup(pushEventController).build();
    }

    @Test
    public void test_handlePushMessageEvent() throws Exception {
        mockMvc.perform(post("/push")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{}")
        )
                .andExpect(status().isOk());


        verify(pushMessageService, times(1)).pushText();
    }
}