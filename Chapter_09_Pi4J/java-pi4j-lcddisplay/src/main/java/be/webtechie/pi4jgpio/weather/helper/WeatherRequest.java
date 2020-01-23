package be.webtechie.pi4jgpio.weather.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Helper to get the forecast from OpenWeatherAPI.
 */
public class WeatherRequest {
    public static String getForecast(String location, String appId) {
        StringBuilder rt = new StringBuilder();

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather"
                    + "?units=metric"
                    + "&q=" + location
                    + "&appid=" + appId);

            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            conection.setRequestMethod("GET");

            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                String readLine;
                while ((readLine = in.readLine()) != null) {
                    rt.append(readLine);
                }
                in.close();
            } else {
                System.err.println("Wrong response code: " + responseCode);
            }
        } catch (Exception ex) {
            System.err.println("Request error: " + ex.getMessage());
        }

        return rt.toString();
    }
}
