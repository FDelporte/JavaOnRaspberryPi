package be.webtechie.lednumberdisplaycontroller;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class LedNumberDisplay extends Pane {

    /*
     A
    F B
     G
    E C
     D
     */

    final LedSegment segmentA;
    final LedSegment segmentB;
    final LedSegment segmentC;
    final LedSegment segmentD;
    final LedSegment segmentE;
    final LedSegment segmentF;
    final LedSegment segmentG;

    public LedNumberDisplay(int width, int height, int segmentHeight, Color idleColor, Color selectedColor) {
        // Set the background color
        this.setStyle("-fx-background-color: #ffffff");

        // Horizontal segments
        this.segmentA = new LedSegment(width, segmentHeight, idleColor);
        this.segmentA.setLayoutX(0);
        this.segmentA.setLayoutY(0);

        this.segmentG = new LedSegment(width, segmentHeight, idleColor);
        this.segmentG.setLayoutX(0);
        this.segmentG.setLayoutY((height / 2) - (segmentHeight / 2));

        this.segmentD = new LedSegment(width, segmentHeight, idleColor);
        this.segmentD.setLayoutX(0);
        this.segmentD.setLayoutY(height - (segmentHeight / 2));

        // Left vertical segments
        this.segmentF = new LedSegment(width, segmentHeight, idleColor);
        this.segmentF.getTransforms().add(new Rotate(90, 0, height / 2, 0, Rotate.Z_AXIS));
        this.segmentF.setLayoutX(0);
        this.segmentF.setLayoutY(0);

        this.segmentE = new LedSegment(width, segmentHeight, idleColor);
        this.segmentE.getTransforms().add(new Rotate(90, 0, height / 2, 0, Rotate.Z_AXIS));
        this.segmentE.setLayoutX(0);
        this.segmentE.setLayoutY(height / 2);

        // Right vertical segments
        this.segmentB = new LedSegment(width, segmentHeight, idleColor);
        this.segmentC = new LedSegment(width, segmentHeight, idleColor);

        this.getChildren().addAll(this.segmentA, this.segmentB, this.segmentC, this.segmentD, this.segmentE, this.segmentF, this.segmentG);
    }
}
