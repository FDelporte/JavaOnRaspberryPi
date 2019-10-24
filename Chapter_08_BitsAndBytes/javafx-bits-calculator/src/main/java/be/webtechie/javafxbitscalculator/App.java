package be.webtechie.javafxbitscalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        var scene = new Scene(new BitsAndBytes(), 1600, 260);
        stage.setTitle("Bits and bytes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}