package be.webtechie.pi4jgpio.weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDescription {
    @JsonProperty("id")
    public long id;

    @JsonProperty("main")
    public String main;

    @JsonProperty("description")
    public String description;

    @JsonProperty("icon")
    public String icon;
}
