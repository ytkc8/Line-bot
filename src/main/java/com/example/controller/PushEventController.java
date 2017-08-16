package com.example.controller;

import com.example.service.PushMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushEventController {
    private PushMessageService pushMessageService;

    public PushEventController(PushMessageService pushMessageService) {
        this.pushMessageService = pushMessageService;
    }

    @PostMapping("/push")
    public void handlePushMessageEvent(){
        pushMessageService.pushText();
    }
}
