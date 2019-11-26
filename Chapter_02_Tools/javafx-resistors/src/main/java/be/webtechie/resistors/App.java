package be.webtechie.resistors;

import be.webtechie.resistors.views.ColorsTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var scene = new Scene(new ColorsTableView(), 1800, 800);
        stage.setScene(scene);
        stage.setTitle("Resistor color coding");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}