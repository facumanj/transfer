package com.weather.service;

import java.util.List;

public interface WeatherRepository {
    List<Object> getForecast(String city);
}
