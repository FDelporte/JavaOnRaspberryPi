package be.webtechie.timeline;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class JavaTimeline extends Pane {

    public JavaTimeline(int width, int height, int offset, DataSet dataSet) {
        // Set the background color
        this.setStyle("-fx-background-color: #ffffff");

        // Draw the main line from left to right
        int axisLength = width - (2 * offset);

        Line horizontalAxis = new Line(offset, offset * 3, axisLength, offset * 3);
        horizontalAxis.setStroke(Color.DARKGREY);
        horizontalAxis.setStrokeWidth(5);
        this.getChildren().add(horizontalAxis);

        // Draw the year lines
        int yearsToDraw = dataSet.getEndYear() - dataSet.getStartYear() + 1;
        double distanceBetweenYears = axisLength / yearsToDraw;

        for (int i = 0; i < yearsToDraw; i++) {
            int currentYear = dataSet.getStartYear() + i;
            double yearLineX = (offset * 3) + (i * distanceBetweenYears);

            Line yearLine = new Line(yearLineX, offset * 2, yearLineX, offset * 4);
            yearLine.setStroke(Color.DARKGREY);
            yearLine.setStrokeWidth(currentYear % 10 == 0 ? 4 : 2);
            this.getChildren().add(yearLine);

            // Add a label for every 10 year
            if (currentYear % 10 == 0) {
                Label yearLabel = new Label(String.valueOf(currentYear));
                yearLabel.setLayoutX(yearLineX - 20);
                yearLabel.setLayoutY(offset * 5);
                yearLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
                this.getChildren().add(yearLabel);
            }

            // Add a special notation
            String notation = dataSet.getEntries().get(currentYear);

            if (notation != null && !notation.isEmpty()) {
                Line notationLine = new Line(yearLineX, offset * 5, yearLineX, offset * 9);
                notationLine.setStroke(Color.RED);
                notationLine.setStrokeWidth(3);
                this.getChildren().add(notationLine);

                int notationLabelWidth = height - (offset * 9);

                Label notationLabel = new Label(String.valueOf(dataSet.getStartYear() + i) + " - " + notation);
                notationLabel.setLayoutX(yearLineX);
                notationLabel.setLayoutY((offset * 9));
                notationLabel.setPrefWidth(notationLabelWidth);
                notationLabel.setPrefHeight(40);
                notationLabel.setStyle("-fx-font-size: 14px; -fx-text-alignment: left;");
                notationLabel.getTransforms().add(new Rotate(70, 0, 20 / 2, 0, Rotate.Z_AXIS));
                this.getChildren().add(notationLabel);
            }
        }
    }
}
