package be.webtechie.resistors;

import be.webtechie.resistors.views.BandCalculator;
import be.webtechie.resistors.views.ColorsTableView;
import be.webtechie.resistors.views.LedResistorCalculator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        HBox holder = new HBox();
        holder.setSpacing(25);
        holder.getChildren().add(new ColorsTableView());

        VBox calculators = new VBox();
        calculators.setSpacing(10);
        holder.getChildren().add(calculators);

        calculators.getChildren().add(new BandCalculator());
        calculators.getChildren().add(new Separator());
        calculators.getChildren().add(new LedResistorCalculator());

        var scene = new Scene(holder, 1500, 400);
        stage.setScene(scene);
        stage.setTitle("Resistor color coding");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}