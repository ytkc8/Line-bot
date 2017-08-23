package com.example.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class DefaultServiceKeyGetter implements ServiceKeyGetter {
    @Value("${service.key.weathers}")
    private String envWeathersText;

    @Override
    public String getServiceKey(String messageText) {
        List<String> weathers = separateStringByColon(envWeathersText);

        for (String weather : weathers) {
            String regex = weather + ".*";
            if (messageText.matches(regex)) {
                return "weather";
            }
        }
        return "default";
    }

    private List<String> separateStringByColon(String originalText) {
        String[] separatedStrings = originalText.split(":");
        return asList(separatedStrings);
    }
}
