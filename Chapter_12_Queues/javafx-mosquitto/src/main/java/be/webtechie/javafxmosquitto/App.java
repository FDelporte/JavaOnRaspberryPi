package be.webtechie.javafxmosquitto;

import be.webtechie.javafxmosquitto.client.QueueClient;
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

        System.out.println("Test");
        
        var label = new Label("Hello, JavaFX.");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}