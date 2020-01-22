package be.webtechie.pi4jgpio.weather.helper;

import static junit.framework.Assert.assertNotNull;

import org.junit.Test;

public class WeatherDescriptionRequestTest {
    // Create an app id by signing up on
    // https://home.openweathermap.org/users/sign_up
    private static final String APP_ID = "9f72246c2183b3e577fb925fafa0cfbf";

    @Test
    public void testRequest() {
        String result = WeatherRequest.getForecast("Passendale", APP_ID);
        assertNotNull(result);

        System.out.println(result);
    }
}
