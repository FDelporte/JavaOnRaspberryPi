package be.webtechie.pi4jgpio;

import be.webtechie.pi4jgpio.lcd.LcdOutput;
import be.webtechie.pi4jgpio.weather.data.Forecast;
import be.webtechie.pi4jgpio.weather.helper.WeatherMapper;
import be.webtechie.pi4jgpio.weather.helper.WeatherRequest;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/LcdExample.java
 */
public class App {

    // Create an app id by signing up on
    // https://home.openweathermap.org/users/sign_up
    private static final String APP_ID = "### FILL IN YOUR APP ID ###";
    private static final String LOCATION = "Passendale";
    private static final int REQUEST_FORECAST_SECONDS = 60;

    public static final int LCD_ROWS = 2;
    public static final int LCD_COLUMNS = 16;

    public static void main(String[] args) {
        System.out.println("Starting LDC display example...");

        try {
            // Initialize the GPIO controller
            final GpioController gpio = GpioFactory.getInstance();

            // initialize LCD
            final GpioLcdDisplay lcd = new GpioLcdDisplay(
                    LCD_ROWS,           // N° of rows
                    LCD_COLUMNS,        // N° of columns
                    RaspiPin.GPIO_11,   // BCM 07: RS pin
                    RaspiPin.GPIO_10,   // BCM 08: Strobe pin
                    RaspiPin.GPIO_00,   // BCM 17: Data bit 1
                    RaspiPin.GPIO_01,   // BCM 18: Data bit 2
                    RaspiPin.GPIO_02,   // BCM 27: Data bit 3
                    RaspiPin.GPIO_03    // BCM 22: Data bit 4
            );

            // Initial output to check if the wiring is OK
            lcd.write(1, "Started...");

            // Initialize the LCD output and start it as a separate thread
            final LcdOutput lcdOutput = new LcdOutput(lcd);
            lcdOutput.run();

            // Continuously get the weather forecast and show on the LCD
            String apiReply;
            while (true) {
                apiReply = WeatherRequest.getForecast(LOCATION, APP_ID);
                if (!apiReply.isEmpty()) {
                    lcdOutput.setForecast(WeatherMapper.getWeather(apiReply));
                }

                Thread.sleep(REQUEST_FORECAST_SECONDS * 1000);
            }

            // Shut down the GPIO controller
            //gpio.shutdown();

            //System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
