package be.webtechie.pinninginfo.views;

import be.webtechie.pinninginfo.util.Converter;
import com.pi4j.boardinfo.definition.PinType;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Visualizes the {@link PinType}.
 */
class PinTypeView extends HBox {
    /**
     * Constructor to create a legend field for a {@link PinType}.
     *
     * @param pinType The {@link PinType} to be visualized.
     */
    PinTypeView(final PinType pinType) {
        this.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid;");
        this.setPadding(new Insets(1, 1, 1, 1));
        this.setSpacing(10);
        this.setPrefHeight(20);

        VBox color = new VBox();
        color.setPrefWidth(25);
        color.setPrefHeight(25);
        color.setBackground(new Background(new BackgroundFill(Converter.intToColor(pinType.getColor()), CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(color);

        Label name = new Label(pinType.getLabel());
        name.setStyle("-fx-font: 12px Tahoma; -fx-font-weight: bold;");
        this.getChildren().add(name);
    }
}
