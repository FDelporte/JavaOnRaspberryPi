package be.webtechie.pinninginfo.views;

import com.pi4j.boardinfo.model.HeaderPin;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
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
     * Constructor to create the visualization of a {@link HeaderPin}.
     *
     * @param pin The {@link HeaderPin} to be visualized.
     * @param rightToLeft True/False to switch between RL and LR visualization.
     */
    PinView(final HeaderPin pin, final boolean rightToLeft) {
        this.setNodeOrientation(rightToLeft ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
        this.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid;");
        this.setPadding(new Insets(1, 1, 1, 1));
        this.setSpacing(5);
        this.setPrefHeight(20);
        this.setAlignment(Pos.CENTER);

        // BCM
        VBox bcmHolder = new VBox();
        this.getChildren().add(bcmHolder);

        Label bcmLabel = new Label();
        bcmLabel.setPrefWidth(20);
        bcmLabel.setStyle("-fx-font: 9px Tahoma; -fx-alignment: TOP-CENTER;");
        bcmHolder.getChildren().add(bcmLabel);

        Label bcmNumber = new Label();
        bcmNumber.setPrefWidth(20);
        bcmNumber.setStyle("-fx-font: 16px Tahoma; -fx-alignment: TOP-CENTER;");
        bcmHolder.getChildren().add(bcmNumber);

        if (pin.getBcmNumber() != null) {
            bcmLabel.setText("BCM");
            bcmNumber.setText(String.valueOf(pin.getBcmNumber()));
        }

        // WiringPi number
        VBox wiringHolder = new VBox();
        this.getChildren().add(wiringHolder);

        Label wiringPiLabel = new Label();
        wiringPiLabel.setPrefWidth(20);
        wiringPiLabel.setStyle("-fx-font: 9px Tahoma; -fx-alignment: TOP-CENTER;");
        wiringHolder.getChildren().add(wiringPiLabel);

        Label wiringPiNumber = new Label();
        wiringPiNumber.setPrefWidth(20);
        wiringPiNumber.setStyle("-fx-font: 16px Tahoma; -fx-alignment: TOP-CENTER;");
        wiringHolder.getChildren().add(wiringPiNumber);

        if (pin.getWiringPiNumber() != null) {
            wiringPiLabel.setText("WPI");
            wiringPiNumber.setText(String.valueOf(pin.getWiringPiNumber()));
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
        VBox pinNumberHolder = new VBox();
        this.getChildren().add(pinNumberHolder);

        Label pinNumberLabel = new Label();
        pinNumberLabel.setPrefWidth(20);
        pinNumberLabel.setStyle("-fx-font: 9px Tahoma; -fx-alignment: TOP-CENTER;");
        pinNumberLabel.setText("PIN");
        pinNumberHolder.getChildren().add(pinNumberLabel);

        Label pinNumber = new Label();
        pinNumber.setPrefWidth(20);
        pinNumber.setStyle("-fx-font: 16px Tahoma; -fx-alignment: TOP-CENTER;");
        pinNumber.setText(String.valueOf(pin.getPinNumber()));
        pinNumberHolder.getChildren().add(pinNumber);

        // Color pin
        VBox color = new VBox();
        color.setPrefWidth(25);
        color.setPrefHeight(25);
        color.setBackground(new Background(new BackgroundFill(
                intToColor(pin.getPinType().getColor()), CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(color);
    }

    public Color intToColor(int value) {
        return Color.rgb(
                (value & 0xFF0000) >> 16,
                (value & 0x00FF00) >> 8,
                (value & 0x0000FF));
    }
}
