package eu.hansolo.fx.colorselector;

import javafx.animation.Interpolator;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Created by hansolo on 21.12.15.
 */
public class ConicalGradient {
    private static final double ANGLE_FACTOR = 1d / 360d;
    private double              centerX;
    private double              centerY;
    private List<Stop>          sortedStops;


    // ******************** Constructors **************************************
    public ConicalGradient(final Stop... STOPS) {
        this(0, 0, Arrays.asList(STOPS));
    }
    public ConicalGradient(final List<Stop> STOPS) {
        this(0, 0, STOPS);
    }
    public ConicalGradient(final double CENTER_X, final double CENTER_Y, final Stop... STOPS) {
        this(CENTER_X, CENTER_Y, 0d, Arrays.asList(STOPS));
    }
    public ConicalGradient(final double CENTER_X, final double CENTER_Y, final List<Stop> STOPS) {
        this(CENTER_X, CENTER_Y, 0d, STOPS);
    }
    public ConicalGradient(final double CENTER_X, final double CENTER_Y, final double OFFSET, final Stop... STOPS) {
        this(CENTER_X, CENTER_Y, OFFSET, Arrays.asList(STOPS));
    }
    public ConicalGradient(final double CENTER_X, final double CENTER_Y, final double OFFSET, final List<Stop> STOPS) {
        double offset  = clamp(0d, 1d, OFFSET);
        centerX        = CENTER_X;
        centerY        = CENTER_Y;
        List<Stop> stops;
        if (null == STOPS || STOPS.isEmpty()) {
            stops = new ArrayList<>();
            stops.add(new Stop(0.0, Color.TRANSPARENT));
            stops.add(new Stop(1.0, Color.TRANSPARENT));
        } else {
            stops = STOPS;
        }
        sortedStops = calculate(stops, offset);
    }


    // ******************** Methods *******************************************
    private List<Stop> calculate(final List<Stop> STOPS, final double OFFSET) {
        List<Stop>       stops = new ArrayList<>(STOPS.size());
        final BigDecimal STEP  = new BigDecimal(Double.MIN_VALUE);
        for (Stop stop : STOPS) {
            BigDecimal newOffsetBD = new BigDecimal(stop.getOffset() + OFFSET).remainder(BigDecimal.ONE);
            if (newOffsetBD.equals(BigDecimal.ZERO)) {
                newOffsetBD = BigDecimal.ONE;
                stops.add(new Stop(Double.MIN_VALUE, stop.getColor()));
            } else if (Double.compare((stop.getOffset() + OFFSET), 1d) > 0) {
                newOffsetBD = newOffsetBD.subtract(STEP);
            }
            stops.add(new Stop(newOffsetBD.doubleValue(), stop.getColor()));
        }

        HashMap<Double, Color> stopMap = new LinkedHashMap<>(stops.size());
        for (Stop stop : stops) { stopMap.put(stop.getOffset(), stop.getColor()); }

        List<Stop>        sortedStops     = new ArrayList<>(stops.size());
        SortedSet<Double> sortedFractions = new TreeSet<>(stopMap.keySet());
        if (sortedFractions.last() < 1) {
            stopMap.put(1.0, stopMap.get(sortedFractions.first()));
            sortedFractions.add(1.0);
        }
        if (sortedFractions.first() > 0) {
            stopMap.put(0.0, stopMap.get(sortedFractions.last()));
            sortedFractions.add(0.0);
        }
        for (double fraction : sortedFractions) { sortedStops.add(new Stop(fraction, stopMap.get(fraction))); }

        return sortedStops;
    }

    public void recalculateWithAngle(final double ANGLE) {
        double angle = ANGLE % 360d;
        sortedStops = calculate(sortedStops, ANGLE_FACTOR * angle);
    }

    public List<Stop> getStops() { return sortedStops; }

    public double[] getCenter() { return new double[]{ centerX, centerY }; }
    public Point2D getCenterPoint() { return new Point2D(centerX, centerY); }

    private static final <T extends Number> T clamp(final T MIN, final T MAX, final T VALUE) {
        if (VALUE.doubleValue() < MIN.doubleValue()) return MIN;
        if (VALUE.doubleValue() > MAX.doubleValue()) return MAX;
        return VALUE;
    }

    public Image getImage(final double WIDTH, final double HEIGHT) {
        int   width  = (int) WIDTH  <= 0 ? 100 : (int) WIDTH;
        int   height = (int) HEIGHT <= 0 ? 100 : (int) HEIGHT;
        Color color  = Color.TRANSPARENT;
        final WritableImage RASTER       = new WritableImage(width, height);
        final PixelWriter   PIXEL_WRITER = RASTER.getPixelWriter();
        if (Double.compare(0d, centerX) == 0) centerX = width * 0.5;
        if (Double.compare(0d, centerY) == 0) centerY = height * 0.5;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double dx = x - centerX;
                double dy = y - centerY;
                double distance = Math.sqrt((dx * dx) + (dy * dy));
                distance = Double.compare(distance, 0) == 0 ? 1 : distance;

                double angle = Math.abs(Math.toDegrees(Math.acos(dx / distance)));
                if (dx >= 0 && dy <= 0) {
                    angle = 90.0 - angle;   // Upper Right Quadrant
                } else if (dx >= 0 && dy >= 0) {
                    angle += 90.0;          // Lower Right Quadrant
                } else if (dx <= 0 && dy >= 0) {
                    angle += 90.0;          // Lower Left Quadrant
                } else if (dx <= 0 && dy <= 0) {
                    angle = 450.0 - angle;  // Upper Left Qudrant
                }

                for (int i = 0; i < (sortedStops.size() - 1); i++) {
                    if (angle >= (sortedStops.get(i).getOffset() * 360d) && angle < (sortedStops.get(i + 1).getOffset() * 360d)) {
                        double fraction = (angle - sortedStops.get(i).getOffset() * 360d) / ((sortedStops.get(i + 1).getOffset() - sortedStops.get(i).getOffset()) * 360d);
                        color = (Color) Interpolator.LINEAR.interpolate(sortedStops.get(i).getColor(), sortedStops.get(i + 1).getColor(), fraction);
                    }
                }
                PIXEL_WRITER.setColor(x, y, color);
            }
        }
        return RASTER;
    }

    public Image getRoundImage(final double SIZE) {
        int   size  = (int) SIZE  <= 0 ? 100 : (int) SIZE;
        Color color = Color.TRANSPARENT;
        final WritableImage RASTER       = new WritableImage(size, size);
        final PixelWriter   PIXEL_WRITER = RASTER.getPixelWriter();
        if (Double.compare(0d, centerX) == 0) centerX = size * 0.5;
        if (Double.compare(0d, centerY) == 0) centerY = size * 0.5;
        double radius = size * 0.5;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                double dx       = x - centerX;
                double dy       = y - centerY;
                double distance = Math.sqrt((dx * dx) + (dy * dy));
                distance = Double.compare(distance, 0) == 0 ? 1 : distance;

                double angle = Math.abs(Math.toDegrees(Math.acos(dx / distance)));

                if (dx >= 0 && dy <= 0) {
                    angle = 90.0 - angle;
                } else if (dx >= 0 && dy >= 0) {
                    angle += 90.0;
                } else if (dx <= 0 && dy >= 0) {
                    angle += 90.0;
                } else if (dx <= 0 && dy <= 0) {
                    angle = 450.0 - angle;
                }
                double radiusMinus05 = radius - 0.25;
                double radiusMinus10 = radius - 0.5;
                double radiusMinus15 = radius - 1.0;
                double radiusMinus20 = radius - 1.5;

                if (distance > radius) {
                    color = Color.TRANSPARENT;
                } else {
                    for (int i = 0; i < (sortedStops.size() - 1); i++) {
                        if (angle >= (sortedStops.get(i).getOffset() * 360) && angle < (sortedStops.get(i + 1).getOffset() * 360)) {
                            double fraction = (angle - sortedStops.get(i).getOffset() * 360) / ((sortedStops.get(i + 1).getOffset() - sortedStops.get(i).getOffset()) * 360);
                            color = (Color) Interpolator.LINEAR.interpolate(sortedStops.get(i).getColor(), sortedStops.get(i + 1).getColor(), fraction);

                            if (distance > radiusMinus05) {
                                color = color.deriveColor(0.0, 1.0, 1.0, 0.25);
                            } else if (distance > radiusMinus10) {
                                color = color.deriveColor(0.0, 1.0, 1.0, 0.45);
                            } else if (distance > radiusMinus15) {
                                color = color.deriveColor(0.0, 1.0, 1.0, 0.65);
                            } else if (distance > radiusMinus20) {
                                color = color.deriveColor(0.0, 1.0, 1.0, 0.85);
                            }
                        }
                    }
                }
                PIXEL_WRITER.setColor(x, y, color);
            }
        }
        return RASTER;
    }

    public ImagePattern apply(final Shape SHAPE) {
        double x      = SHAPE.getLayoutBounds().getMinX();
        double y      = SHAPE.getLayoutBounds().getMinY();
        double width  = SHAPE.getLayoutBounds().getWidth();
        double height = SHAPE.getLayoutBounds().getHeight();
        centerX       = width * 0.5;
        centerY       = height * 0.5;
        return new ImagePattern(getImage(width, height), x, y, width, height, false);
    }

    public ImagePattern getImagePattern(final Bounds BOUNDS) {
        return getImagePattern(new Rectangle(BOUNDS.getMinX(), BOUNDS.getMinY(), BOUNDS.getWidth(), BOUNDS.getHeight()));
    }
    public ImagePattern getImagePattern(final Rectangle BOUNDS) {
        double x      = BOUNDS.getX();
        double y      = BOUNDS.getY();
        double width  = BOUNDS.getWidth();
        double height = BOUNDS.getHeight();
        centerX       = width * 0.5;
        centerY       = height * 0.5;
        return new ImagePattern(getImage(width, height), x, y, width, height, false);
    }
}
