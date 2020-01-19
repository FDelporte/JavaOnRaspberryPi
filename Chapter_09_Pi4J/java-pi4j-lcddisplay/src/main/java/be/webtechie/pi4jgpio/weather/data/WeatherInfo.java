package be.webtechie.pi4jgpio.weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherInfo {
    @JsonProperty("temp")
    public float temperature;

    @JsonProperty("feels_like")
    public float temperatureFeeling;

    @JsonProperty("temp_min")
    public float temperatureMinimum;

    @JsonProperty("temp_max")
    public float temperatureMaximum;

    @JsonProperty("pressure")
    public int pressure;

    @JsonProperty("humidity")
    public int humidity;
}
