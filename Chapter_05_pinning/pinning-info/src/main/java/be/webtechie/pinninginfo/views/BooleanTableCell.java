package be.webtechie.pinninginfo.views;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.paint.Color;

/**
 * View helper function to colorize a table cell green (true) or red (false) depending on the value of its cell.
 */
public class BooleanTableCell extends TableCell<TableRow, Boolean> {
    @Override
    protected void updateItem(Boolean value, boolean empty) {
        super.updateItem(value, empty);

        if (empty) {
            this.setText("");
            this.setStyle("-fx-font-weight: bold; -fx-background-color: none; -fx-alignment: TOP-CENTER;");
        } else {
            this.setText(String.valueOf(value).toUpperCase());

            if (value) {
                this.setTextFill(Color.BLACK);
                this.setStyle("-fx-font-weight: bold; -fx-background-color: lightgreen; -fx-alignment: TOP-CENTER;");
            } else {
                this.setTextFill(Color.BLACK);
                this.setStyle("-fx-font-weight: normal; -fx-background-color: orangered; -fx-alignment: TOP-CENTER;");
            }
        }
    }
}