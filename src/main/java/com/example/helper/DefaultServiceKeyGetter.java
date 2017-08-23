package com.example.helper;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.util.List;

import static java.lang.ClassLoader.getSystemResourceAsStream;

@Component
public class DefaultServiceKeyGetter implements ServiceKeyGetter {
    @Override
    public String getServiceKey(String messageText) {
        List weathers = new Yaml().loadAs(getSystemResourceAsStream("weathers.yml"), List.class);

        for (Object weather : weathers) {
            String regex = weather.toString() + ".*";
            if (messageText.matches(regex)) {
                return "weather";
            }
        }
        return "default";
    }
}
