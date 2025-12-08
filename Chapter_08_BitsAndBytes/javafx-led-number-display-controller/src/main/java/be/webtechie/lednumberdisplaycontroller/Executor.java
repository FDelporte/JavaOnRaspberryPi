package be.webtechie.lednumberdisplaycontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Execute the given function in a terminal
 */
class Executor {

    /**
     * Hide the constructor.
     * This is not really needed, but a preferred way in a class
     * with only static methods.
     */
    private Executor() {
        // NOP
    }

    /**
     * Execute the given command, this is called by the public methods.
     *
     * @param cmd String array command to be executed.
     */
    public static String execute(String[] cmd) {
        try {
            // Create a ProcessBuilder with the command and arguments
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);

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

            System.out.println("'" + String.join(" ", cmd) + "' returned: " + output);

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