package com.weather.controller;

import com.weather.dto.ForecastDto;
import com.weather.service.ForecastServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path="/data")
public class ForecastController {

    @Autowired
    ForecastServiceImpl forecastService;

    @RequestMapping(method=GET)
    public ForecastDto getAverages(@PathVariable("city") String city) {
         return forecastService.getAverages(city);
    }
}
