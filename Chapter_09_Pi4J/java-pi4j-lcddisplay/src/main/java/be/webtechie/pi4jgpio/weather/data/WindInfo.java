package be.webtechie.pi4jgpio.weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WindInfo {
    @JsonProperty("speed")
    public float speed;

    @JsonProperty("deg")
    public int degrees;
}
