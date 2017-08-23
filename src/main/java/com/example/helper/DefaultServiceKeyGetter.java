package com.example.helper;

import org.springframework.stereotype.Component;

@Component
public class DefaultServiceKeyGetter implements ServiceKeyGetter {
    @Override
    public String getServiceKey(String messageText) {
        // todo: this logic is not tested
        String key = "default";
        if ("天気".equals(messageText)) {
            key = "weather";
        }
        return "";
    }
}
