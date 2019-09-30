package be.webtechie.javafxmosquitto;

import be.webtechie.javafxmosquitto.client.QueueClient;
import be.webtechie.javafxmosquitto.ui.QueueMonitor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private QueueClient queueClient;

    @Override
    public void start(Stage stage) {
        this.queueClient = new QueueClient();

        var scene = new Scene(new QueueMonitor(this.queueClient), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}