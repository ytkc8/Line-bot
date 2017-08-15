package com.example.wrapper;

import com.example.model.OWMResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.example.helper.OpenWeatherMapAPIUriGetter.getUri;

@Component
public class DefaultOpenWeatherMapAPIWrapper implements OpenWeatherMapAPIWrapper {
    private final RestTemplate restTemplate;

    public DefaultOpenWeatherMapAPIWrapper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OWMResponse getWeatherData() {
        return restTemplate.getForObject(getUri(), OWMResponse.class);
    }
}
