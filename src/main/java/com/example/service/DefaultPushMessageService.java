package com.example.service;

import com.example.wrapper.PushWrapper;
import org.springframework.stereotype.Service;

@Service
public class DefaultPushMessageService implements PushMessageService {
    private PushWrapper pushWrapper;

    public DefaultPushMessageService(PushWrapper pushWrapper) {
        this.pushWrapper = pushWrapper;
    }

    @Override
    public void pushText() {

    }
}
