package be.webtechie.lednumberdisplaycontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LedNumberDisplay extends Pane {

    private final List<LedSegmentDefinition> segments = new ArrayList<>();

    final LedSegment segmentA;
    final LedSegment segmentB;
    final LedSegment segmentC;
    final LedSegment segmentD;
    final LedSegment segmentE;
    final LedSegment segmentF;
    final LedSegment segmentG;
    final DotSegment segmentDot;

    /*
     A
    F B
     G
    E C
     D
     */

    public LedNumberDisplay(LedType ledType, Color idleColor, Color selectedColor) {
        // Set the background color
        this.setStyle("-fx-background-color: #ffffff");

        // Horizontal segments
        this.segmentA = new LedSegment(ledType.getSegmentDefinition("A"), idleColor, ledType.getWidth(), ledType.getHeight());
        this.segmentB = new LedSegment(ledType.getSegmentDefinition("B"), idleColor, ledType.getWidth(), ledType.getHeight());
        this.segmentC = new LedSegment(ledType.getSegmentDefinition("C"), idleColor, ledType.getWidth(), ledType.getHeight());
        this.segmentD = new LedSegment(ledType.getSegmentDefinition("D"), idleColor, ledType.getWidth(), ledType.getHeight());
        this.segmentE = new LedSegment(ledType.getSegmentDefinition("E"), idleColor, ledType.getWidth(), ledType.getHeight());
        this.segmentF = new LedSegment(ledType.getSegmentDefinition("F"), idleColor, ledType.getWidth(), ledType.getHeight());
        this.segmentG = new LedSegment(ledType.getSegmentDefinition("G"), idleColor, ledType.getWidth(), ledType.getHeight());
        this.segmentDot = new DotSegment(
                ledType.getDotDiameter(),
                ledType.getWidth() - ledType.getDotDiameter(),
                ledType.getHeight() - ledType.getDotDiameter(),
                idleColor,
                ledType.getWidth(),
                ledType.getHeight());

        this.getChildren().addAll(this.segmentA, this.segmentB, this.segmentC, this.segmentD, this.segmentE, this.segmentF, this.segmentG, this.segmentDot);
    }

}
