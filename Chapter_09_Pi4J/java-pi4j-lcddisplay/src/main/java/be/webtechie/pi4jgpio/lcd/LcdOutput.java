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

    private boolean running = true;
    private Forecast forecast = null;
    private long lastUpdate = 0;
    private int contentStep = 0;

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
                this.showForecast();
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
        try {
            lcd.clear();
            Thread.sleep(1000);

            lcd.write(LCD_ROW_1, "Weather for " + forecast.name);
            lcd.write(LCD_ROW_2, "Received on " +
                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        } catch (Exception ex) {
            System.err.println("Error while handling content for the LCD: " + ex.getMessage());
        }
    }

    private void showTemperatures() {
        try {
            lcd.clear();
            Thread.sleep(3000);

            // left alignment, full line data
            lcd.write(LCD_ROW_2, "----------------");
            Thread.sleep(500);
            lcd.writeln(LCD_ROW_2, "<< LEFT");
            Thread.sleep(1000);

            // right alignment, full line data
            lcd.write(LCD_ROW_2, "----------------");
            Thread.sleep(500);
            lcd.writeln(LCD_ROW_2, "RIGHT >>", LCDTextAlignment.ALIGN_RIGHT);
            Thread.sleep(1000);

            // center alignment, full line data
            lcd.write(LCD_ROW_2, "----------------");
            Thread.sleep(500);
            lcd.writeln(LCD_ROW_2, "<< CENTER >>", LCDTextAlignment.ALIGN_CENTER);
            Thread.sleep(1000);
        } catch (Exception ex) {
            System.err.println("Error while handling content for the LCD: " + ex.getMessage());
        }
    }

    private void showForecast() {
        try {
            lcd.clear();
            Thread.sleep(1000);
            // mixed alignments, partial line data
            lcd.write(LCD_ROW_2, "----------------");
            Thread.sleep(500);
            lcd.write(LCD_ROW_2, "<L>", LCDTextAlignment.ALIGN_LEFT);
            lcd.write(LCD_ROW_2, "<R>", LCDTextAlignment.ALIGN_RIGHT);
            lcd.write(LCD_ROW_2, "CC", LCDTextAlignment.ALIGN_CENTER);
            Thread.sleep(3000);
        } catch (Exception ex) {
            System.err.println("Error while handling content for the LCD: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        while (this.running) {
            if (this.forecast != null
                    && (System.currentTimeMillis() - this.lastUpdate) > CONTENT_INTERVAL) {
                this.showContent();
                this.lastUpdate = System.currentTimeMillis();
            }
        }
    }
}
