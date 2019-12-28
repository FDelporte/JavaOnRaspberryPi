package be.webtechie;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(true);
        
        var scene = new Scene(new FxScreen(), 640, 480);
        stage.setScene(scene);
        stage.show();

        // Make sure the application quits completely on close
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }

}