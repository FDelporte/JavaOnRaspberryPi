package be.webtechie;

import be.webtechie.pinninginfo.views.HeaderPinView;
import be.webtechie.pinninginfo.views.HeaderTableView;
import com.pi4j.boardinfo.definition.HeaderPins;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private HBox headerHolder;
    private HBox tableHolder;
    private ComboBox<HeaderPins> headerSelection;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        var holder = new HBox();
        holder.setSpacing(25);

        var leftColumn = new VBox();
        leftColumn.setSpacing(25);
        holder.getChildren().add(leftColumn);

        var selection = new HBox();
        selection.setSpacing(25);
        selection.setPadding(new Insets(5));
        leftColumn.getChildren().add(selection);

        var select = new Label("Select the header");
        select.setStyle("-fx-font: 18px Tahoma; -fx-font-weight: bold; -fx-alignment: TOP-CENTER;");
        selection.getChildren().add(select);

        this.headerSelection = new ComboBox<>();
        this.headerSelection.getItems().setAll(HeaderPins.values());
        this.headerSelection.setOnAction(this::drawHeaders);
        selection.getChildren().add(this.headerSelection);

        this.headerHolder = new HBox();
        leftColumn.getChildren().add(this.headerHolder);

        this.tableHolder = new HBox();
        holder.getChildren().add(this.tableHolder);

        var scene = new Scene(holder, 1800, 1000);
        stage.setScene(scene);
        stage.setTitle("Raspberry Pi headers");
        stage.show();
    }

    private void drawHeaders(ActionEvent actionEvent) {
        var headerPins = this.headerSelection.getValue();

        this.headerHolder.getChildren().clear();
        this.headerHolder.getChildren().add(new HeaderPinView(headerPins));

        this.tableHolder.getChildren().clear();
        this.tableHolder.getChildren().add(new HeaderTableView(headerPins));
    }
}