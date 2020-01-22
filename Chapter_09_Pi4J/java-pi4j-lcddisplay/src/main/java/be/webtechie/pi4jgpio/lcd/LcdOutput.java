package be.webtechie.pi4jgpio.lcd;

import be.webtechie.pi4jgpio.weather.data.Forecast;
import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LcdOutput implements Runnable {
    private static final int LCD_ROW_1 = 0;
    private static final int LCD_ROW_2 = 1;
    private static final int LCD_BITS = 4;
    private static final int CONTENT_INTERVAL = 2500;

    private final GpioLcdDisplay lcd;

    private boolean running = false;
    private Forecast forecast = null;
    private long lastUpdate = 0;
    private int contentStep = 0;

    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    public LcdOutput(GpioLcdDisplay lcd) {
        this.lcd = lcd;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    private void stop() {
        this.running = false;
    }

    public void showContent() {
        switch (this.contentStep) {
            case 0:
                this.showTimestamp();
                break;
            case 1:
                this.showTemperatures();
                break;
            case 2:
                this.showDescription();
                break;
            default:
                System.err.println("Step not defined");
        }

        this.contentStep++;

        if (this.contentStep > 2) {
            this.contentStep = 0;
        }
    }

    private void showTimestamp() {
        System.out.println("Showing timestamp for " + forecast.name);

        try {
            lcd.clear();
            Thread.sleep(1000);

            lcd.write(LCD_ROW_1, forecast.name, 
                LCDTextAlignment.ALIGN_CENTER);
            lcd.write(LCD_ROW_2, formatter.format(new Date()), 
                LCDTextAlignment.ALIGN_CENTER);
        } catch (Exception ex) {
            System.err.println("Error while handling content for the LCD: " + ex.getMessage());
        }
    }

    private void showTemperatures() {
        System.out.println("Showing temperature " + forecast.weatherInfo.temperature);

        try {
            lcd.clear();
            Thread.sleep(1000);

            lcd.write(LCD_ROW_1, "Temp", 
                LCDTextAlignment.ALIGN_LEFT);
            lcd.write(LCD_ROW_1, String.valueOf(forecast.weatherInfo.temperature), 
                LCDTextAlignment.ALIGN_RIGHT);
            lcd.write(LCD_ROW_2, "Min/Max " 
                + String.valueOf(forecast.weatherInfo.temperatureMinimum)
                + "/" 
                + String.valueOf(forecast.weatherInfo.temperatureMaximum), 
                LCDTextAlignment.ALIGN_CENTER);
        } catch (Exception ex) {
            System.err.println("Error while handling content for the LCD: " + ex.getMessage());
        }
    }

    private void showDescription() {
        System.out.println("Showing description " + forecast.weatherDescription.get(0).description);

        try {
            lcd.clear();
            Thread.sleep(1000);

            lcd.write(LCD_ROW_1, forecast.weatherDescription.get(0).main, 
                LCDTextAlignment.ALIGN_CENTER);
            lcd.write(LCD_ROW_2, "Humidity " + forecast.weatherInfo.humidity + "%", 
                LCDTextAlignment.ALIGN_CENTER);
        } catch (Exception ex) {
            System.err.println("Error while handling content for the LCD: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        this.running = true;

        try {
            while (this.running) {
                if (this.forecast != null
                        && (System.currentTimeMillis() - this.lastUpdate) > CONTENT_INTERVAL) {
                    this.showContent();
                    this.lastUpdate = System.currentTimeMillis();
                }

                Thread.sleep(100);
            }
        } catch (Exception ex) {
            System.err.println("Error in LCD output thread: " + ex.getMessage());

            this.running = false;
        }
    }

    public boolean isRunning() {
        return this.running;
    }
}
