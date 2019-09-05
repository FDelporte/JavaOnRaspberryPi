package be.webtechie.lednumberdisplaycontroller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DotSegment extends Canvas {

    public DotSegment(int diameter, int x, int y, Color fillColor, int width, int height) {
        super(width, height);

        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(fillColor);
        gc.fillOval(x, y, diameter, diameter);
    }
}
