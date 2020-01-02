package be.webtechie;

import be.webtechie.piheaders.definition.HeaderPins;
import be.webtechie.piheaders.pin.HeaderPin;
import be.webtechie.pinninginfo.views.HeaderPinView;
import be.webtechie.pinninginfo.views.HeaderTableView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * JavaFX App
 */
public class App extends Application {

    private HBox headerViews;
    private ComboBox<HeaderPins> headerSelection;

    @Override
    public void start(Stage stage) {
        VBox holder = new VBox();

        HBox selectionHolder = new HBox();
        selectionHolder.setSpacing(25);
        selectionHolder.setPadding(new Insets(5));
        holder.getChildren().add(selectionHolder);

        Label select = new Label("Select the header");
        select.setStyle("-fx-font: 18px Tahoma; -fx-font-weight: bold; -fx-alignment: TOP-CENTER;");
        selectionHolder.getChildren().add(select);

        this.headerSelection = new ComboBox<>();
        this.headerSelection.getItems().setAll(HeaderPins.values());
        this.headerSelection.setOnAction(this::drawHeaders);
        selectionHolder.getChildren().add(this.headerSelection);

        this.headerViews = new HBox();
        this.headerViews.setSpacing(25);
        this.headerViews.setPadding(new Insets(5));
        holder.getChildren().add(this.headerViews);

        var scene = new Scene(holder, 1800, 800);
        stage.setScene(scene);
        stage.setTitle("Raspberry Pi headers");
        stage.show();
    }

    private void drawHeaders(ActionEvent actionEvent) {
        HeaderPins headerPins = this.headerSelection.getValue();

        this.headerViews.getChildren().clear();
        this.headerViews.getChildren().add(new HeaderPinView(headerPins));
        this.headerViews.getChildren().add(new HeaderTableView(headerPins));
    }

    public static void main(String[] args) {
        launch();
    }
}