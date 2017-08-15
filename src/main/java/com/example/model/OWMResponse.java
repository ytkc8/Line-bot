package com.example.model;

import java.util.List;

public class OWMResponse {
    private List<WeatherData> list;

    public OWMResponse() {
    }

    public OWMResponse(List<WeatherData> list) {
        this.list = list;
    }

    public List<WeatherData> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OWMResponse that = (OWMResponse) o;

        return list != null ? list.equals(that.list) : that.list == null;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OWMResponse{" +
                "list=" + list +
                '}';
    }
}
