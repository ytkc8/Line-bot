package com.example.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ScheduleControllerTest {
    private ScheduleController scheduleController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        scheduleController = new ScheduleController();
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Test
    public void test_wakeUp_endpoint() throws Exception {
        mockMvc.perform(get("/wakeup"))
                .andExpect(status().isOk());
    }
}