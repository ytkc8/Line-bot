package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.ZoneId.SHORT_IDS;

@RestController
public class ScheduleController {
    public ScheduleController() {
    }

    @GetMapping("/wakeup")
    public void wakeup() {
        System.out.println("Good morning!! Current time is " + LocalDateTime.now(ZoneId.of("JST", SHORT_IDS)));
    }
}
