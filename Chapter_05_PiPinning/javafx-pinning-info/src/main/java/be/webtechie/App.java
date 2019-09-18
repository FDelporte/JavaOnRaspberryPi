package be.webtechie;

import be.webtechie.pinninginfo.data.RaspberryPiHeader;
import be.webtechie.pinninginfo.views.HeaderPinView;
import be.webtechie.pinninginfo.views.HeaderTableView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        HBox holder = new HBox();
        holder.setSpacing(25);
        holder.setPadding(new Insets(5));
        holder.getChildren().add(new HeaderPinView(RaspberryPiHeader.get40PinsHeader()));
        holder.getChildren().add(new HeaderTableView(RaspberryPiHeader.get40PinsHeader()));

        var scene = new Scene(holder, 1800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}