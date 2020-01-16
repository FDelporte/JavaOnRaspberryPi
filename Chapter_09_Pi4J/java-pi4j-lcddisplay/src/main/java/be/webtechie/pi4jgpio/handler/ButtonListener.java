package be.webtechie.pi4jgpio.handler;

import static be.webtechie.pi4jgpio.App.LCD_ROW_2;

import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ButtonListener implements GpioPinListenerDigital {

    private final GpioLcdDisplay lcd;

    public ButtonListener(GpioLcdDisplay lcd) {
        this.lcd = lcd;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        if (event.getState() == PinState.LOW) {
            this.lcd.writeln(LCD_ROW_2, event.getPin().getName() + " PRESSED", LCDTextAlignment.ALIGN_CENTER);
        } else {
            this.lcd.writeln(LCD_ROW_2, event.getPin().getName() + " RELEASED", LCDTextAlignment.ALIGN_CENTER);
        }
    }
}
