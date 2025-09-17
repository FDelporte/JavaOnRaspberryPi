package be.webtechie.javafxmosquitto;

import be.webtechie.javafxmosquitto.client.QueueClient;
import be.webtechie.javafxmosquitto.ui.QueueMonitor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private QueueClient queueClient;

    static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.queueClient = new QueueClient("192.168.0.213");

        var scene = new Scene(new QueueMonitor(this.queueClient), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}