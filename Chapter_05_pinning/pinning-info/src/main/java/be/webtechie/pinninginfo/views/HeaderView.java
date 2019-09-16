package be.webtechie.pinninginfo.views;

import be.webtechie.pinninginfo.data.HeaderPin;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Visualizes the header in two columns, as it looks on the board.
 */
public class HeaderView extends VBox {

    /**
     * Constructor to create the visualization of the header.
     *
     * @param pins {@link List} of {@link HeaderPin} for which the header must be visualized.
     * @param extended True for extended view, false for compact view.
     */
    public HeaderView(List<HeaderPin> pins, boolean extended) {
        HBox rows = new HBox();

        rows.getChildren().add(this.getRow(pins, extended, true));
        rows.getChildren().add(this.getRow(pins, extended, false));

        this.getChildren().add(rows);
    }

    /**
     * Visualizes one side of the header.
     *
     * @param pins {@link List} of {@link HeaderPin} for which the header must be visualized.
     * @param extended True for extended view, false for compact view.
     * @param firstRow True for first row, false for second.
     *
     * @return {@link VBox} with the pins of the header row.
     */
    private VBox getRow(List<HeaderPin> pins, boolean extended, boolean firstRow) {
        final VBox vBox = new VBox();
        vBox.setSpacing(1);
        vBox.setPadding(new Insets(1, 1, 1, 1));

        for (HeaderPin pin : pins) {
            vBox.getChildren().add(new PinView(pin, extended, !firstRow));
        }

        return vBox;
    }
}
