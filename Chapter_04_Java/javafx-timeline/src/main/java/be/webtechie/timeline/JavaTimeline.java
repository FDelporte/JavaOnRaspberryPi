package be.webtechie.timeline;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class JavaTimeline extends Pane {

    private static final int TOP_SPACE = 400;
    private final int offset;
    private final DataSet dataSet;
    private final double axisLength;
    private final int numberOfYearsToDraw;
    private final double distanceBetweenYears;

    public JavaTimeline(int width, int height, int offset, DataSet dataSet) {
        this.offset = offset;
        this.dataSet = dataSet;

        // Set the background color
        this.setStyle("-fx-background-color: #ffffff");


        // Draw the main line from left to right
        axisLength = width - (2 * offset);
        numberOfYearsToDraw = dataSet.getEndYear() - dataSet.getStartYear() + 1;
        distanceBetweenYears = axisLength / numberOfYearsToDraw;

        drawData();
        drawYears();
        drawAxis();

        if (dataSet == DataSet.JAVA_RELEASES) {
            drawProjectPanama();
        }
    }

    private void drawAxis() {
        var horizontalAxis = new Line(offset, (offset * 3) + TOP_SPACE, axisLength, (offset * 3) + TOP_SPACE);
        horizontalAxis.setStroke(Color.DARKGREY);
        horizontalAxis.setStrokeWidth(5);
        this.getChildren().add(horizontalAxis);
    }

    private void drawYears() {
        for (int i = 0; i < numberOfYearsToDraw; i++) {
            int currentYear = dataSet.getStartYear() + i;
            double yearLineX = getYearMonthPosition(currentYear, 1);

            var yearLine = new Line(yearLineX, (offset * 2) + TOP_SPACE, yearLineX, (offset * 4) + TOP_SPACE);
            yearLine.setStroke(Color.DARKGREY);
            yearLine.setStrokeWidth(currentYear % 10 == 0 ? 4 : 2);
            this.getChildren().add(yearLine);

            // Add a label for every 5 years
            if (currentYear % 5 == 0) {
                var yearLabel = new Label(String.valueOf(currentYear));
                yearLabel.setLayoutX(yearLineX - 20);
                yearLabel.setLayoutY((offset * 6) + TOP_SPACE);
                yearLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
                this.getChildren().add(yearLabel);
            }
        }
    }

    private double getYearMonthPosition(int year, int month) {
        var yearOffset = year - dataSet.getStartYear();
        return (offset * 3) + (yearOffset * distanceBetweenYears) + (month * (distanceBetweenYears / 11.0));
    }

    private void drawData() {
        var releases = dataSet.getReleases();
        for (int i = 0; i < releases.size(); i++) {
            boolean aboveAxis = i % 2 == 1; // true for odd indices (1, 3, 5, ...)
            drawData(releases.get(i), dataSet.isAlternate() && aboveAxis);
        }
    }

    private void drawData(DataSet.Release release, boolean aboveAxis) {
        double yearLineX = getYearMonthPosition(release.year(), release.month());

        var notationLine = new Line(yearLineX, TOP_SPACE - (offset * 2), yearLineX, (offset * 8) + TOP_SPACE);
        notationLine.setStroke(Color.RED);
        notationLine.setStrokeWidth(3);
        this.getChildren().add(notationLine);

        var notationLabel = new Label(release.year()
                + "/" + (release.month() < 10 ? "0" + release.month() : release.month())
                + ": " + release.description());
        notationLabel.setPrefWidth(500);
        notationLabel.setPrefHeight(40);
        notationLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; ");
        if (aboveAxis) {
            notationLabel.setAlignment(Pos.CENTER_RIGHT);
            // Position the label so its right edge aligns with the notation line
            notationLabel.setLayoutX(yearLineX - 500); // Move left by the full width
            notationLabel.setLayoutY(TOP_SPACE - (offset * 5));
            // Rotate around the right edge of the label (500, 20) to keep it aligned with yearLineX
            notationLabel.getTransforms().add(new Rotate(70, 500, 20, 0, Rotate.Z_AXIS));
        } else {
            notationLabel.setLayoutX(yearLineX);
            notationLabel.setLayoutY((offset * 7) + TOP_SPACE);
            // Rotate around the left edge of the label (0, 20) to keep it aligned with yearLineX
            notationLabel.getTransforms().add(new Rotate(70, 0, 20, 0, Rotate.Z_AXIS));
        }

        this.getChildren().add(notationLabel);
    }

    private void drawProjectPanama() {
        addLabelBox("Project Panama", findRelease(8), findRelease(30), offset, 18, Color.LIGHTBLUE);

        // Foreign Memory Access API
        addLabelBox("Foreign Memory Access API", findRelease(8), findRelease(30), offset * 6, 14, Color.LIGHTGRAY);
        addLabelBox("370", 14, offset * 6, Color.ORANGERED);
        addLabelBox("383", 15, offset * 6, Color.ORANGERED);
        addLabelBox("393", 16, offset * 6, Color.ORANGERED);

        // Foreign Linker API
        addLabelBox("Foreign Linker API", findRelease(8), findRelease(30), offset * 9, 14, Color.LIGHTGRAY);
        addLabelBox("389", 16, offset * 9, Color.MAGENTA);

        // Combined Foreign Function & Memory API
        addLabelBox("Foreign Function & Memory API", findRelease(8), findRelease(30), offset * 12, 14, Color.LIGHTGRAY);
        addLabelBox("412", 17, offset * 12, Color.TURQUOISE);
        addLabelBox("419", 18, offset * 12, Color.TURQUOISE);
        addLabelBox("424", 19, offset * 12, Color.TURQUOISE);
        addLabelBox("434", 20, offset * 12, Color.TURQUOISE);
        addLabelBox("442", 21, offset * 12, Color.TURQUOISE);
        addLabelBox("454", 22, offset * 12, Color.GREEN);

        // Vector API
        addLabelBox("Vector API", findRelease(8), findRelease(30), offset * 15, 14, Color.LIGHTGRAY);
        addLabelBox("338", 16, offset * 15, Color.ORANGE);
        addLabelBox("414", 17, offset * 15, Color.ORANGE);
        addLabelBox("417", 18, offset * 15, Color.ORANGE);
        addLabelBox("426", 19, offset * 15, Color.ORANGE);
        addLabelBox("438", 20, offset * 15, Color.ORANGE);
        addLabelBox("448", 21, offset * 15, Color.ORANGE);
        addLabelBox("460", 22, offset * 15, Color.ORANGE);
        addLabelBox("469", 23, offset * 15, Color.ORANGE);
        addLabelBox("489", 24, offset * 15, Color.ORANGE);
        addLabelBox("508", 25, offset * 15, Color.ORANGE);
        addLabelBox("???", 26, offset * 15, Color.ORANGE);
        addLabelBox("???", 27, offset * 15, Color.ORANGE);
        addLabelBox("???", 28, offset * 15, Color.ORANGE);
        addLabelBox("???", 29, offset * 15, Color.ORANGE);
        addLabelBox("???", 30, offset * 15, Color.ORANGE);
    }

    private DataSet.Release findRelease(int javaVersion) {
        return dataSet.getReleases().stream()
                .filter(r -> r.description().contains(" " + javaVersion))
                .findFirst()
                .orElseThrow();
    }

    private void addLabelBox(String label, int javaVersion, int labelY, Color fill) {
        addLabelBox(label, findRelease(javaVersion - 1), findRelease(javaVersion), labelY + 3, 11, fill);
    }

    private void addLabelBox(String label, DataSet.Release startRelease, DataSet.Release endRelease, int labelY, int fontSize, Color fill) {
        double yearXStart = getYearMonthPosition(startRelease.year(), startRelease.month());
        double yearXEnd = getYearMonthPosition(endRelease.year(), endRelease.month());

        // Calculate the width of the box
        double boxWidth = yearXEnd - yearXStart;

        // Create a background rectangle
        var backgroundBox = new Rectangle();
        backgroundBox.setX(yearXStart);
        backgroundBox.setY(labelY);
        backgroundBox.setWidth(boxWidth);
        backgroundBox.setHeight(fontSize * 2);
        backgroundBox.setFill(fill);
        backgroundBox.setStroke(Color.BLACK);
        backgroundBox.setStrokeWidth(1);
        this.getChildren().add(backgroundBox);

        // Create the label
        var lbl = new Label(label);
        lbl.setLayoutX(yearXStart + 3);
        lbl.setLayoutY(labelY + (fontSize / 2) - 3);
        lbl.setStyle("-fx-font-size: " + fontSize + "px;");
        this.getChildren().add(lbl);
    }
}
