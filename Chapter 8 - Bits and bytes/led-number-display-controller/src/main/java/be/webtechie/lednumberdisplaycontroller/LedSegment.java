package be.webtechie.lednumberdisplaycontroller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LedSegment extends Canvas {
    public LedSegment(int width, int height, Color color) {
        super(width, height);

        int cornerWidth = height / 2;

        GraphicsContext gc = this.getGraphicsContext2D();

        gc.setFill(color);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2d);

        double[] pointsX = new double[]{0, cornerWidth, width - cornerWidth, width, width - cornerWidth, cornerWidth, 0};
        double[] pointsY = new double[]{height / 2, 0, 0 , height / 2, height, height, height / 2};

        gc.fillPolygon(pointsX, pointsY, pointsX.length);
        gc.strokePolygon(pointsX, pointsY, pointsX.length);
    }
}
