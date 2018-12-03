package com.weather.dto;

public class ForecastDto {
    Double dailyTemperatureAverage;
    Double nightlyTemperatureAverage;
    Double pressureAverage;

    public Double getDailyTemperatureAverage() {
        return dailyTemperatureAverage;
    }

    public void setDailyTemperatureAverage(Double dailyTemperatureAverage) {
        this.dailyTemperatureAverage = dailyTemperatureAverage;
    }

    public Double getNightlyTemperatureAverage() {
        return nightlyTemperatureAverage;
    }

    public void setNightlyTemperatureAverage(Double nightlyTemperatureAverage) {
        this.nightlyTemperatureAverage = nightlyTemperatureAverage;
    }

    public Double getPressureAverage() {
        return pressureAverage;
    }

    public void setPressureAverage(Double pressureAverage) {
        this.pressureAverage = pressureAverage;
    }
}
