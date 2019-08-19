import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // The scene which is the "root" of our application
        Scene scene = new Scene(new BitsAndBytes(), 400, 300);

        // The top level JavaJX container
        stage.setTitle("Minimal JavaFX 11 application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}