package be.webtechie.timeline;

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
        int width = 1200;
        int height = 1200;

        var scene = new Scene(new JavaTimeline(width, height, 10, DataSet.LANGUAGE_BIRTHDAYS), width + 50, height);
        stage.setScene(scene);
        stage.show();
    }
}