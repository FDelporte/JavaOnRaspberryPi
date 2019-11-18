package be.webtechie.lednumberdisplaycontroller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        var scene = new Scene(new SegmentSelection(), 640, 330);
        stage.setScene(scene);
        stage.setTitle("LED Segment display");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}