package com.example.wrapper;

import com.example.model.OWMResponse;

public interface OpenWeatherMapAPIWrapper {
    OWMResponse getWeatherData();
}
