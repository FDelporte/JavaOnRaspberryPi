package be.webtechie.pi4jgpio.weather.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import be.webtechie.pi4jgpio.weather.data.Forecast;
import org.junit.Before;
import org.junit.Test;

public class WeatherDescriptionMapperTest {
    private Forecast forecast;

    @Before
    public void init() {
        this.forecast = WeatherMapper.getWeather("{\"coord\":{\"lon\":2.89,\"lat\":50.85},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":3.52,\"feels_like\":-0.42,\"temp_min\":0.56,\"temp_max\":7.22,\"pressure\":1046,\"humidity\":86},\"visibility\":10000,\"wind\":{\"speed\":3.1,\"deg\":350},\"clouds\":{\"all\":0},\"dt\":1579469707,\"sys\":{\"type\":1,\"id\":6559,\"country\":\"BE\",\"sunrise\":1579419691,\"sunset\":1579450561},\"timezone\":3600,\"id\":2795100,\"name\":\"Ypres\",\"cod\":200}\n");
        assertNotNull(this.forecast);
    }

    @Test
    public void testBase() {
        assertEquals("Ypres", this.forecast.name);
    }

    @Test
    public void testCoordinates() {
        assertEquals(2.89, this.forecast.coordinates.longitude, 0.01);
        assertEquals(50.85, this.forecast.coordinates.latitude, 0.01);
    }

    @Test
    public void testWeatherDescription() {
        assertEquals(1, this.forecast.weatherDescription.size());
        assertEquals("Clear", this.forecast.weatherDescription.get(0).main);
        assertEquals("clear sky", this.forecast.weatherDescription.get(0).description);
        assertEquals("01n", this.forecast.weatherDescription.get(0).icon);
    }

    @Test
    public void testWeatherInfo() {
        assertEquals(3.52, this.forecast.weatherInfo.temperature, 0.01);
        assertEquals(-0.42, this.forecast.weatherInfo.temperatureFeeling, 0.01);
        assertEquals(0.56, this.forecast.weatherInfo.temperatureMinimum, 0.01);
        assertEquals(7.22, this.forecast.weatherInfo.temperatureMaximum, 0.01);
        assertEquals(1046, this.forecast.weatherInfo.pressure);
        assertEquals(86, this.forecast.weatherInfo.humidity);
    }

    @Test
    public void testWindInfo() {
        assertEquals(3.1, this.forecast.windInfo.speed, 0.01);
        assertEquals(350, this.forecast.windInfo.degrees);
    }
}
