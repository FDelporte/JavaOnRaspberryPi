package be.webtechie.timeline;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        int width = 900;
        int height = 500;

        var scene = new Scene(new JavaTimeline(width, height, 10, DataSet.RASPBERRYPI_BOARDS), width + 50, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}