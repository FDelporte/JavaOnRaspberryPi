package be.webtechie.i2c;

import be.webtechie.i2c.definition.Board;
import be.webtechie.i2c.definition.Relay;
import be.webtechie.i2c.definition.State;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Controller to set the relay states on different boards.
 */
public class RelayController {

    /**
     * Set the state of the all the relays on the given boards.
     *
     * @param boards List of Enum Board
     * @param relays List of Enum Relay
     * @param state Enum State
     */
    public static void setRelays(List<Board> boards, List<Relay> relays, State state) {
        for (Board board : boards) {
            for (Relay relay : relays) {
                setRelay(board, relay, state);
            }
        }
    }

    /**
     * Set the state of the relay on the given board.
     *
     * @param board Enum Board
     * @param relay Enum Relay
     * @param state Enum State
     */
    public static void setRelay(Board board, Relay relay, State state) {
        execute(new String[]{
                "i2cset",
                "-y",
                "1",
                String.format("0x%02X", board.getAddress()),
                String.format("0x%02X", relay.getChannel()),
                String.format("0x%02X", state.getValue())
        });

        System.out.println(relay + " on " + board + " set to " + state);
    }

    /**
     * Execute the given command, this is called by the public methods.
     *
     * @param cmd String array command to be executed.
     */
    private static String execute(String[] cmd) {
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
