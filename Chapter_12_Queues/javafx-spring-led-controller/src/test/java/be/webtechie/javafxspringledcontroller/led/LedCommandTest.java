package be.webtechie.javafxspringledcontroller.led;

import static org.junit.Assert.assertEquals;

import javafx.scene.paint.Color;
import org.junit.Test;

public class LedCommandTest {

    @Test
    public void commandToString() {
        LedCommand ledCommand = new LedCommand(
                LedEffect.BLINKING,
                5,
                Color.rgb(255, 0, 0),
                Color.rgb(0, 0, 255)
        );

        assertEquals("3:5:255:0:0:0:0:255", ledCommand.toCommandString());
    }

    @Test
    public void stringToCommand() {
        LedCommand ledCommand = new LedCommand("3:5:255:0:0:0:255:0");

        assertEquals(LedEffect.BLINKING, ledCommand.getLedEffect());
        assertEquals(5, ledCommand.getSpeed());
        assertEquals(Color.rgb(255, 0, 0), ledCommand.getColor1());
        assertEquals(Color.rgb(0, 255, 0), ledCommand.getColor2());
    }
}
