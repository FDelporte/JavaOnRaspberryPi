package be.webtechie.pi4j;

import be.webtechie.pi4j.definition.Board;
import be.webtechie.pi4j.definition.Relay;
import be.webtechie.pi4j.definition.State;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Controller to set the relay states on different boards.
 */
public class RelayController {

    /**
     * Set the state of the relay on the given board.
     *
     * @param board Enum Board
     * @param relay Enum Relay
     * @param state Enum State
     */
    public static void setRelay(Board board, Relay relay, State state) {
        String cmd = "i2cset -y 1"
                + " " + String.format("0x%02X", board.getAddress())
                + " " + String.format("0x%02X", relay.getChannel())
                + " " + String.format("0x%02X", state.getValue());

        System.out.println("Setting relay on board with command: " + cmd);

        final String result = execute(cmd);

        System.out.println("Result: " + result);
    }

    /**
     * Execute the given command, this is called by the public methods.
     *
     * @param cmd String command to be executed.
     */
    private static String execute(String cmd) {
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

            System.out.println(cmd);

            // We don't need the process anymore.
            p.destroy();

            // Return the result of the command.
            return output.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());

            return "";
        }
    }
}
