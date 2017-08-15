package com.example.wrapper;

import com.example.model.OWMResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DefaultOpenWeatherMapAPIWrapper implements OpenWeatherMapAPIWrapper {
    private RestTemplate restTemplate;

    public DefaultOpenWeatherMapAPIWrapper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OWMResponse getWeatherData() {
        String uri = getUri();
        return restTemplate.getForObject(uri, OWMResponse.class);
    }

    private String getUri() {
        String host = "api.openweathermap.org";
        String apiPath = "/data/2.5/forecast";
        String cityParam = "q=Tokyo";
        String unitParam = "units=Metric";
        String cntParam = "cnt=1";
        String apiKey = "appid=3a981d4a71950ac6430af06740e589b2";
        return "http://" + host + apiPath + "?" +
                cityParam + "&" + unitParam + "&" + cntParam + "&" + apiKey;
    }
}
