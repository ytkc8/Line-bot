package com.example.model;

public class Weather {
    private String main;
    private String description;

    public Weather() {
    }

    public Weather(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (main != null ? !main.equals(weather.main) : weather.main != null) return false;
        return description != null ? description.equals(weather.description) : weather.description == null;
    }

    @Override
    public int hashCode() {
        int result = main != null ? main.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
