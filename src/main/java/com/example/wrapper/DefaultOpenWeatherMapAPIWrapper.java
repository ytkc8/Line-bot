package com.example.wrapper;

import com.example.helper.APIUriGetter;
import com.example.model.OWMResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DefaultOpenWeatherMapAPIWrapper implements OpenWeatherMapAPIWrapper {
    private final RestTemplate restTemplate;
    private final APIUriGetter APIUriGetter;

    public DefaultOpenWeatherMapAPIWrapper(
            RestTemplate restTemplate,
            APIUriGetter APIUriGetter
    ) {
        this.restTemplate = restTemplate;
        this.APIUriGetter = APIUriGetter;
    }

    @Override
    public OWMResponse getWeatherData() {
        return restTemplate.getForObject(
                APIUriGetter.getUri(),
                OWMResponse.class
        );
    }
}
