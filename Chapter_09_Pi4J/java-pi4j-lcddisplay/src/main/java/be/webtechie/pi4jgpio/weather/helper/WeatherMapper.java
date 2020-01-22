package be.webtechie.pi4jgpio.weather.helper;

import be.webtechie.pi4jgpio.weather.data.Forecast;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Helper to convert the json received from OpenWeatherAPI to Java objects.
 */
public class WeatherMapper {
    public static Forecast getWeather(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonString, Forecast.class);
        } catch (IOException ex) {
            System.err.println("Unable to parse the given string to Forecast object: " + ex.getMessage());
            return null;
        }
    }
}
