package be.webtechie.lednumberdisplaycontroller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LedSegment extends Canvas {

    public LedSegment(LedSegmentDefinition ledSegmentDefinition, Color fillColor, int width, int height) {
        super(width, height);

        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(fillColor);
        gc.fillPolygon(
                ledSegmentDefinition.getPointsX(),
                ledSegmentDefinition.getPointsY(),
                ledSegmentDefinition.getPointsX().length);
    }
}
