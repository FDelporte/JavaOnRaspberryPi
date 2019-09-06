package be.webtechie.lednumberdisplaycontroller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LedSegment extends Canvas {

    final GraphicsContext gc;

    final LedSegmentDefinition ledSegmentDefinition;
    final int width;
    final int height;

    public LedSegment(LedSegmentDefinition ledSegmentDefinition, Color fillColor, int width, int height) {
        super(width, height);

        this.gc = this.getGraphicsContext2D();
        this.ledSegmentDefinition = ledSegmentDefinition;
        this.width = width;
        this.height = height;

        this.draw(fillColor);

    }

    public void setColor(Color color) {
        this.draw(color);
    }

    private void draw(Color color) {
        this.gc.clearRect(0, 0, this.width, this.height);
        this.gc.setFill(color);
        this.gc.fillPolygon(
                ledSegmentDefinition.getPointsX(),
                ledSegmentDefinition.getPointsY(),
                ledSegmentDefinition.getPointsX().length);
    }
}
