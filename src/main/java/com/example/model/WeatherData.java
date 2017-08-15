package com.example.model;

import java.util.List;

public class WeatherData {
    private String dt;
    private List<Weather> weather;

    public WeatherData() {
    }

    public WeatherData(String dt, List<Weather> weather) {
        this.dt = dt;
        this.weather = weather;
    }

    public String getDt() {
        return dt;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherData that = (WeatherData) o;

        if (dt != null ? !dt.equals(that.dt) : that.dt != null) return false;
        return weather != null ? weather.equals(that.weather) : that.weather == null;
    }

    @Override
    public int hashCode() {
        int result = dt != null ? dt.hashCode() : 0;
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "dt='" + dt + '\'' +
                ", weather=" + weather +
                '}';
    }
}
