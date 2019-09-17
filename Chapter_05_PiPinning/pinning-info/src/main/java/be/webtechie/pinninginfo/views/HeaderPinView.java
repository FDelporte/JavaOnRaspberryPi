package be.webtechie.pinninginfo.views;

import be.webtechie.pinninginfo.data.HeaderPin;
import be.webtechie.pinninginfo.data.PinType;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Visualizes the header in two columns, as it looks on the board.
 */
public class HeaderPinView extends VBox {

    /**
     * Constructor to create the pin visualization of the header.
     *
     * @param pins {@link List} of {@link HeaderPin} for which the header must be visualized.
     */
    public HeaderPinView(List<HeaderPin> pins) {
        this.setSpacing(25);

        HBox rows = new HBox();
        rows.getChildren().add(this.getRow(pins, true));
        rows.getChildren().add(this.getRow(pins,  false));
        this.getChildren().add(rows);

        this.getChildren().add(this.getLegend());
    }

    /**
     * Visualizes the legend for the header.
     *
     * @return {@link VBox} with the pin types.
     */
    private VBox getLegend() {
        VBox vBox = new VBox();
        vBox.setSpacing(1);
        vBox.setPadding(new Insets(1, 1, 1, 1));
        vBox.setMaxWidth(200);

        for (PinType pinType : PinType.values()) {
            vBox.getChildren().add(new PinTypeView(pinType));
        }

        return vBox;
    }

    /**
     * Visualizes one side of the header.
     *
     * @param pins {@link List} of {@link HeaderPin} for which the header must be visualized.
     * @param firstRow True for first row, false for second.
     *
     * @return {@link VBox} with the pins of the header row.
     */
    private VBox getRow(List<HeaderPin> pins, boolean firstRow) {
        VBox vBox = new VBox();
        vBox.setSpacing(1);
        vBox.setPadding(new Insets(1, 1, 1, 1));

        for (int i = (firstRow ? 0 : 1); i < pins.size(); i += 2) {
            vBox.getChildren().add(new PinView(pins.get(i), !firstRow));
        }

        return vBox;
    }
}
