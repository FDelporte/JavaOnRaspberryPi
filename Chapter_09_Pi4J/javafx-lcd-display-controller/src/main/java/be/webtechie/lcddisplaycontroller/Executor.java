package be.webtechie.lcddisplaycontroller;

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
     * @param cmd String command to be executed.
     */
    static void execute(String cmd) {
        try {
            // Get a process to be able to do native calls on the operating system.
            // You can compare this to opening a terminal window and running a command.
            Process p = Runtime.getRuntime().exec(cmd);

            // Get the error stream of the process and print it 
            // so we will now if something goes wrong.
            InputStream error = p.getErrorStream();
            for (int i = 0; i < error.available(); i++) {
                System.out.println("" + error.read());
            }

            // Get the output stream, this is the result of the command we give.
            String line;
            StringBuilder output = new StringBuilder();
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                output.append(line);
            }
            input.close();

            System.out.println("Executed: " + cmd);

            // We don't need the process anymore.
            p.destroy();

            // Return the result of the command.
            System.out.println(output.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}