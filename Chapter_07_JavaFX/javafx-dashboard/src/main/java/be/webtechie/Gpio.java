package be.webtechie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Change and read a pin using gpioget and gpioset.
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
     * Set the state of the pin high or low.
     *
     * @param pin The pin number according to the WiringPi numbering scheme
     * @param on True or False
     */
    public static void setPinState(final int pin, final boolean on) {
        System.out.println("Setting pin " + pin + " to " + on);

        execute("gpioset -c gpiochip0 -t0 " + pin + "=" + (on ? " 1" : " 0"));
    }

    /**
     * Get the state of the pin.
     *
     * @param pin The pin number according to the WiringPi numbering scheme
	 * @return Flag if the pin is high (1 = true) or low (0 = false)
     */
    public static boolean getPinState(final int pin) {
        final String result = execute("gpioget -c gpiochip0 " + pin);

        System.out.println("Getting pin state of " + pin + ", result: " + result);

        return result.contains(pin + "=active");
    }

    /**
     * Execute the given command, this is called by the public methods.
     *
     * @param cmd String command to be executed.
     */
    private static String execute(String cmd) {
        try {
            // Split the command string into program and arguments
            String[] cmdArray = cmd.split("\\s+");

            // Create a ProcessBuilder with the command and arguments
            ProcessBuilder processBuilder = new ProcessBuilder(cmdArray);

            // Redirect error stream to output stream for easier handling
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process p = processBuilder.start();

            // Get the output stream, this is the result of the command we give.
            StringBuilder output = new StringBuilder();
            try (BufferedReader input = new BufferedReader(
                    new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    output.append(line);
                }
            }

            // Wait for the process to complete
            p.waitFor();

            System.out.println(cmd);

            // Return the result of the command.
            return output.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "";
        } catch (InterruptedException e) {
            System.err.println("Process was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
            return "";
        }
    }
}