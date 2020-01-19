package be.webtechie.pi4jgpio.weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {
    @JsonProperty("lon")
    public float longitude;

    @JsonProperty("lat")
    public float latitude;
}
