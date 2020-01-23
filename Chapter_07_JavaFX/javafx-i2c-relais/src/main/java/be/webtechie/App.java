package be.webtechie;

import be.webtechie.ui.ToggleSwitchScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Inspired by https://wiki.52pi.com/index.php/DockerPi_4_Channel_Relay_SKU:_EP-0099
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        var scene = new Scene(new ToggleSwitchScreen(), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}