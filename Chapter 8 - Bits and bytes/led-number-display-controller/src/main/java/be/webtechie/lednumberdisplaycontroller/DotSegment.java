package be.webtechie.lednumberdisplaycontroller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DotSegment extends Canvas {

    final GraphicsContext gc;

    final int diameter;
    final int x;
    final int y;
    final int width;
    final int height;

    public DotSegment(int diameter, int x, int y, Color fillColor, int width, int height) {
        super(width, height);

        this.gc = this.getGraphicsContext2D();
        this.diameter = diameter;
        this.x = x;
        this.y = y;
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
        this.gc.fillOval(x, y, diameter, diameter);
    }
}
