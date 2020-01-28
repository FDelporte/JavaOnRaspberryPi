package be.webtechie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Change a pin state using WiringPi and command line calls
 * http://wiringpi.com/the-gpio-utility/
 */
public class Gpio {

    /**
     * Hide the constructor.
     * This is not really needed, but a preferred way in a class 
     * with only static methods.
     */
    private Gpio() {
        // NOP
    }

    /**
     * Initialize the pin so it can be toggled later.
     *
     * @param pin The pin number according to the WiringPi numbering scheme
     */
    public static void initiatePin(final int pin, final String mode) {
        execute("gpio mode " + pin + " " + mode);
    }

    /**
     * Set the state of the pin high or low.
     *
     * @param pin The pin number according to the WiringPi numbering scheme
     * @param on True or False
     */
    public static void setPinState(final int pin, final boolean on) {
        System.out.println("Setting pin " + pin + " to " + on);

        execute("gpio write " + pin + (on ? " 1" : " 0"));
    }

    /**
     * Get the state of the pin.
     *
     * @param pin The pin number according to the WiringPi numbering scheme
	 * @return Flag if the pin is high (1 = true) or low (0 = false)
     */
    public static boolean getPinState(final int pin) {
        final String result = execute("gpio read " + pin);

        System.out.println("Getting pin state of " + pin + ", result: " + result);

        return result.equals("1");
    }

    /**
     * Execute the given command, this is called by the public methods.
     *
     * @param cmd String command to be executed.
     */
    private static String execute(String cmd) {
        Process p = null;
        InputStream error = null;
        BufferedReader input = null;

        try {
            // Get a process to be able to do native calls on the operating system.
            // You can compare this to opening a terminal window and running a command.
            p = Runtime.getRuntime().exec(cmd);

            // Get the error stream of the process and print it 
            // so we will now if something goes wrong.
            error = p.getErrorStream();
            for (int i = 0; i < error.available(); i++) {
                System.out.println("" + error.read());
            }

            // Get the output stream, this is the result of the command we give.
            String line;
            StringBuilder output = new StringBuilder();
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                output.append(line);
            }
            input.close();

            System.out.println(cmd);

            // Return the result of the command.
            return output.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());

            return "";
        } finally {
            if (p != null) {
                p.destroy();
            }

            if (error != null) {
                try {
                    error.close();
                } catch (IOException ex) {
                    System.err.println("Error while closing the error stream");
                }
            }

            if (input != null) {
                try {
                input.close();
                } catch (IOException ex) {
                    System.err.println("Error while closing the input stream");
                }
            }
        }
    }
}