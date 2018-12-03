package com.weather.service;

import com.weather.dto.ForecastDto;

public interface ForecastService {
    ForecastDto getAverages(String city);
}
