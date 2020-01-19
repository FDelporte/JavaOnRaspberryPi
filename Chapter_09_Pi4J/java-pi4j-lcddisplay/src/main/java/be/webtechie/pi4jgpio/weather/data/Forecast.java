package be.webtechie.pi4jgpio.weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Forecast {
    @JsonProperty("coord")
    public Coordinates coordinates;

    @JsonProperty("weather")
    public List<WeatherDescription> weatherDescription;

    @JsonProperty("main")
    public WeatherInfo weatherInfo;

    @JsonProperty("wind")
    public WindInfo windInfo;

    @JsonProperty("name")
    public String name;
}
