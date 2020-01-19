package be.webtechie.pi4jgpio.lcd;

import be.webtechie.pi4jgpio.weather.data.Forecast;
import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LcdOutput {
    public static final int LCD_ROW_1 = 0;
    public static final int LCD_ROW_2 = 1;
    public static final int LCD_BITS = 4;

    public static void showForecast(GpioLcdDisplay lcd, Forecast forecast) {
        try {
            lcd.clear();
            Thread.sleep(1000);

            lcd.write(LCD_ROW_1, "Weather for " + forecast.name);
            lcd.write(LCD_ROW_2, "Received on " +
                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

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
}
