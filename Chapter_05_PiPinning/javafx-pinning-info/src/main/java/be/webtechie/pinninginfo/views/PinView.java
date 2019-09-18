package be.webtechie.pinninginfo.views;

import be.webtechie.pinninginfo.data.HeaderPin;
import be.webtechie.pinninginfo.data.RaspberryPiHeader;
import java.util.stream.Collectors;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Visualizes the {@link HeaderPin}.
 */
class PinView extends HBox {

    /**
     * Constructor to create the visualization of a {@link RaspberryPiHeader}.
     *
     * @param pin The {@link HeaderPin} to be visualized.
     * @param rightToLeft True/False to switch between RL and LR visualization.
     */
    PinView(final HeaderPin pin, final boolean rightToLeft) {
        this.setNodeOrientation(rightToLeft ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
        this.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid;");
        this.setPadding(new Insets(1, 1, 1, 1));
        this.setSpacing(1);
        this.setPrefHeight(20);
        this.setAlignment(Pos.CENTER);

        // GPIO number
        Label gpioNumber = new Label();
        gpioNumber.setPrefWidth(20);
        gpioNumber.setStyle("-fx-font: 16px Tahoma; -fx-alignment: TOP-CENTER;");
        this.getChildren().add(gpioNumber);

        if (pin.getWiringPiNumber() != null) {
            gpioNumber.setText(String.valueOf(pin.getWiringPiNumber()));
        }

        // Tooltip
        final Tooltip tooltip = new Tooltip();
        if (pin.getRemark() != null) {
            tooltip.setText(pin.getRemark());
        }

        // Name and info
        Label name = new Label(pin.getName());
        name.setStyle("-fx-font: 12px Tahoma; -fx-font-weight: bold;");
        name.setTooltip(tooltip);
        name.setMinWidth(180);
        this.getChildren().add(name);

        // Pin number
        Label pinNumber = new Label();
        pinNumber.setPrefWidth(20);
        pinNumber.setStyle("-fx-font: 16px Tahoma; -fx-alignment: TOP-CENTER;");
        pinNumber.setText(String.valueOf(pin.getPinNumber()));
        this.getChildren().add(pinNumber);

        // Color pin
        VBox color = new VBox();
        color.setPrefWidth(25);
        color.setPrefHeight(25);
        color.setBackground(new Background(new BackgroundFill(pin.getPinType().getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(color);
    }
}
