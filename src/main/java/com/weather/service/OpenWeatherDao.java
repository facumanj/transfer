package com.weather.service;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpenWeatherRepository implements WeatherRepository {
    @Override
    public List<Object> getForecast(String city) {
        return null;
    }
}
