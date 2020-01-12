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

    private HBox headerHolder;
    private HBox tableholder;
    private ComboBox<HeaderPins> headerSelection;

    @Override
    public void start(Stage stage) {
        HBox holder = new HBox();
        holder.setSpacing(25);

        VBox leftColumn = new VBox();
        leftColumn.setSpacing(25);
        holder.getChildren().add(leftColumn);

        HBox selection = new HBox();
        selection.setSpacing(25);
        selection.setPadding(new Insets(5));
        leftColumn.getChildren().add(selection);

        Label select = new Label("Select the header");
        select.setStyle("-fx-font: 18px Tahoma; -fx-font-weight: bold; -fx-alignment: TOP-CENTER;");
        selection.getChildren().add(select);

        this.headerSelection = new ComboBox<>();
        this.headerSelection.getItems().setAll(HeaderPins.values());
        this.headerSelection.setOnAction(this::drawHeaders);
        selection.getChildren().add(this.headerSelection);

        this.headerHolder = new HBox();
        leftColumn.getChildren().add(this.headerHolder);

        this.tableholder = new HBox();
        holder.getChildren().add(this.tableholder);

        var scene = new Scene(holder, 1800, 800);
        stage.setScene(scene);
        stage.setTitle("Raspberry Pi headers");
        stage.show();
    }

    private void drawHeaders(ActionEvent actionEvent) {
        HeaderPins headerPins = this.headerSelection.getValue();

        this.headerHolder.getChildren().clear();
        this.headerHolder.getChildren().add(new HeaderPinView(headerPins));

        this.tableholder.getChildren().clear();
        this.tableholder.getChildren().add(new HeaderTableView(headerPins));
    }

    public static void main(String[] args) {
        launch();
    }
}