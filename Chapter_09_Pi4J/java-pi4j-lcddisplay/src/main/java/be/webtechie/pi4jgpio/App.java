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
    private static final String LOCATION = "Ypres";
    private static final int REQUEST_SECONDS = 1;

    public static final int LCD_ROWS = 2;
    public static final int LCD_COLUMNS = 16;

    public static void main(String[] args) {
        System.out.println("Starting LDC display example...");

        try {
            // Initialize the GPIO controller
            final GpioController gpio = GpioFactory.getInstance();

            // initialize LCD
            final GpioLcdDisplay lcd = new GpioLcdDisplay(LCD_ROWS,    // number of row supported by LCD
                    LCD_COLUMNS,       // number of columns supported by LCD
                    RaspiPin.GPIO_11,  // LCD RS pin
                    RaspiPin.GPIO_10,  // LCD strobe pin
                    RaspiPin.GPIO_00,  // LCD data bit 1
                    RaspiPin.GPIO_01,  // LCD data bit 2
                    RaspiPin.GPIO_02,  // LCD data bit 3
                    RaspiPin.GPIO_03); // LCD data bit 4

            // Continuously get the weather forecast and show on the LCD
            String apiReply;
            Forecast forecast;
            while (true) {
                apiReply = WeatherRequest.getForecast(LOCATION, APP_ID);

                if (apiReply != "") {
                    forecast = WeatherMapper.getWeather(apiReply);

                    if (forecast != null) {
                        LcdOutput.showForecast(lcd, forecast);
                    }
                }

                Thread.sleep(REQUEST_SECONDS * 1000);
            }

            // Shut down the GPIO controller
            //gpio.shutdown();

            //System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
