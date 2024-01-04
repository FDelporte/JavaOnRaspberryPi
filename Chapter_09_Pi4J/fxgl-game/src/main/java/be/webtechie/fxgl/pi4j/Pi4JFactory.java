package be.webtechie.fxgl.pi4j;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.util.Console;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getExecutor;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class Pi4JFactory {

    // BCM numbers of the connected components
    // Full list can be found on https://pinout.xyz/pinout/picade_hat
    private static final int PIN_JOYSTICK_UP = 12;
    private static final int PIN_JOYSTICK_DOWN = 6;
    private static final int PIN_JOYSTICK_LEFT = 20;
    private static final int PIN_JOYSTICK_RIGHT = 16;
    private static final int PIN_BUTTON_1 = 5;

    private final Console console;
    private Context pi4j;

    public Pi4JFactory() {
        console = new Console();
        try {
            pi4j = Pi4J.newAutoContext();
            initInputGpios();
        } catch (Exception ex) {
            console.println("Error while initializing Pi4J: {}", ex.getMessage());
        }
    }

    public Console getConsole() {
        return console;
    }

    /**
     * Initialize Pi4J and the connected components
     */
    private void initInputGpios() {
        try {
            // Print program title/header
            console.title("<-- The Pi4J Project -->", "FXGL Example project");

            // Joystick inputs
            initInputGpio("JoystickUp", PIN_JOYSTICK_UP, KeyCode.UP);
            initInputGpio("JoystickDown", PIN_JOYSTICK_DOWN, KeyCode.DOWN);
            initInputGpio("JoystickLeft", PIN_JOYSTICK_LEFT, KeyCode.LEFT);
            initInputGpio("JoystickRight", PIN_JOYSTICK_RIGHT, KeyCode.RIGHT);

            // Push button inputs
            initInputGpio("ButtonBullet", PIN_BUTTON_1, KeyCode.SPACE);
        } catch (Exception ex) {
            console.println("Error while initializing Pi4J: " + ex.getMessage());
        }
    }

    private void initInputGpio(String id, int bcm, KeyCode keyCode) {
        var input = pi4j.create(DigitalInput.newConfigBuilder(pi4j)
                .id(id)
                .address(bcm)
                .pull(PullResistance.PULL_UP)
                .debounce(3000L)
                .provider("pigpio-digital-input"));
        input.addListener(e -> {
            if (e.state() == DigitalState.LOW) {
                console.println("Input change for " + id);
                getExecutor().startAsyncFX(() -> getInput().mockKeyPress(keyCode));
            } else {
                getExecutor().startAsyncFX(() -> getInput().mockKeyRelease(keyCode));
            }
        });

    }
}
