package com.example.wrapper;

import com.example.helper.OpenWeatherMapAPIUriGetter;
import com.example.model.OWMResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DefaultOpenWeatherMapAPIWrapper implements OpenWeatherMapAPIWrapper {
    private final RestTemplate restTemplate;
    private final OpenWeatherMapAPIUriGetter openWeatherMapAPIUriGetter;

    public DefaultOpenWeatherMapAPIWrapper(
            RestTemplate restTemplate,
            OpenWeatherMapAPIUriGetter openWeatherMapAPIUriGetter
    ) {
        this.restTemplate = restTemplate;
        this.openWeatherMapAPIUriGetter = openWeatherMapAPIUriGetter;
    }

    @Override
    public OWMResponse getWeatherData() {
        return restTemplate.getForObject(
                openWeatherMapAPIUriGetter.getUri(),
                OWMResponse.class
        );
    }
}
