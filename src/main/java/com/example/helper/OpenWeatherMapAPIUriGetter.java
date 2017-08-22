package com.example.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherMapAPIUriGetter implements APIUriGetter {
    @Value("${open.weather.map.api.key}")
    private String apiKeyValue;

    @Override
    public String getUri() {
        String host = "api.openweathermap.org";
        String apiPath = "/data/2.5/forecast";
        String cityParam = "q=Tokyo";
        String unitParam = "units=Metric";
        String cntParam = "cnt=1";
        String apiKey = "appid=" + apiKeyValue;
        return "http://" + host + apiPath +
                "?" + cityParam + "&" + unitParam + "&" + cntParam + "&" + apiKey;
    }
}
