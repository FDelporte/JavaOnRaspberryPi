package be.webtechie.pi4jgpio;

import be.webtechie.pi4jgpio.handler.ButtonListener;
import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import java.text.SimpleDateFormat;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/LcdExample.java
 */
public class App {

    public static final int LCD_ROWS = 2;
    public static final int LCD_ROW_1 = 0;
    public static final int LCD_ROW_2 = 1;
    public static final int LCD_COLUMNS = 16;
    public static final int LCD_BITS = 4;

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

            // provision gpio pins as input pins with its internal pull up resistor enabled
            GpioPinDigitalInput btn1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_13, "B1", PinPullResistance.PULL_UP);
            gpio.addListener(new ButtonListener(lcd), btn1);
            GpioPinDigitalInput btn2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, "B2", PinPullResistance.PULL_UP);
            gpio.addListener(new ButtonListener(lcd), btn2);
            GpioPinDigitalInput btn3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, "B3", PinPullResistance.PULL_UP);
            gpio.addListener(new ButtonListener(lcd), btn3);
            GpioPinDigitalInput btn4 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12, "B4", PinPullResistance.PULL_UP);
            gpio.addListener(new ButtonListener(lcd), btn4);

            // clear LCD
            lcd.clear();
            Thread.sleep(1000);

            // write line 1 to LCD
            lcd.write(LCD_ROW_1, "The Pi4J Project");

            // write line 2 to LCD
            lcd.write(LCD_ROW_2, "----------------");

            // line data replacement
            for (int index = 0; index < 5; index++) {
                lcd.write(LCD_ROW_2, "----------------");
                Thread.sleep(500);
                lcd.write(LCD_ROW_2, "****************");
                Thread.sleep(500);
            }
            lcd.write(LCD_ROW_2, "----------------");

            // single character data replacement
            for (int index = 0; index < lcd.getColumnCount(); index++) {
                lcd.write(LCD_ROW_2, index, ">");
                if (index > 0) {
                    lcd.write(LCD_ROW_2, index - 1, "-");
                }
                Thread.sleep(300);
            }
            for (int index = lcd.getColumnCount() - 1; index >= 0; index--) {
                lcd.write(LCD_ROW_2, index, "<");
                if (index < lcd.getColumnCount() - 1) {
                    lcd.write(LCD_ROW_2, index + 1, "-");
                }
                Thread.sleep(300);
            }

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

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

            // update time
            /* TODO
            while (true) {
                // write time to line 2 on LCD
                if (gpio.isHigh(myButtons)) {
                    lcd.writeln(LCD_ROW_2, formatter.format(new Date()), LCDTextAlignment.ALIGN_CENTER);
                }
                Thread.sleep(1000);
            }
            */

            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            gpio.shutdown();
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        System.out.println("Done");
    }
}
