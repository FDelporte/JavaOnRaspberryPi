package be.webtechie.lednumberdisplaycontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        var scene = new Scene(new SegmentSelection(copyShiftScriptFile()), 500, 350);
        stage.setScene(scene);
        stage.setTitle("LED Segment display");
        stage.show();
    }

    /**
     * Copy the Python file "shift.py" from the resources directory to the tmp directory.
     *
     * @return The path of the copied file.
     */
    private String copyShiftScriptFile() {
        try (InputStream is = App.class.getResourceAsStream("/shift.py")) {
            if (is == null) {
                System.err.println("shift.py file not found in the jar");
                return "";
            }

            File shiftFile = File.createTempFile("shift_", ".py");
            try (OutputStream os = new FileOutputStream(shiftFile)) {
                int readBytes;
                byte[] buffer = new byte[4096];

                while ((readBytes = is.read(buffer)) > 0) {
                    os.write(buffer, 0, readBytes);
                }
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }

            return shiftFile.toString();
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }

        return "";
    }
}