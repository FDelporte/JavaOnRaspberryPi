package be.webtechie;

import be.webtechie.pinninginfo.data.RaspberryPiHeader;
import be.webtechie.pinninginfo.views.HeaderView;
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
        var scene = new Scene(new HeaderView(RaspberryPiHeader.get40PinsHeader(), true), 640, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}