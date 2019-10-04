package eu.hansolo.fx.colorselector;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;


/**
 * User: hansolo
 * Date: 29.02.16
 * Time: 08:29
 */
public class Main extends Application {

    private ColorSelector colorSelector;

    @Override public void init() {
        Stop[] stops = { new Stop(0.0, Color.rgb(255,255,0)),
                          new Stop(0.125, Color.rgb(255,0,0)),
                          new Stop(0.375, Color.rgb(255,0,255)),
                          new Stop(0.5, Color.rgb(0,0,255)),
                          new Stop(0.625, Color.rgb(0,255,255)),
                          new Stop(0.875, Color.rgb(0,255,0)),
                          new Stop(1.0, Color.rgb(255,255,0)) };

        colorSelector = new ColorSelector(stops);
        colorSelector.setPrefSize(400, 400);

        colorSelector.selectedColorProperty().addListener(o -> System.out.println(colorSelector.getSelectedColor()));
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane(colorSelector);
        pane.setPadding(new Insets(10));
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(31, 31, 31), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane);

        stage.setTitle("Color Selector");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
