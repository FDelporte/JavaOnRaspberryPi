package eu.hansolo.fx.colorselector;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Scale;


/**
 * User: hansolo
 * Date: 28.02.16
 * Time: 12:20
 */
public class ColorSelector extends Region {
    private static final double   PREFERRED_WIDTH  = 250;
    private static final double   PREFERRED_HEIGHT = 250;
    private static final double   MINIMUM_WIDTH    = 50;
    private static final double   MINIMUM_HEIGHT   = 50;
    private static final double   MAXIMUM_WIDTH    = 1024;
    private static final double   MAXIMUM_HEIGHT   = 1024;
    private double                size;
    private Shape                 ring;
    private double                scaleFactor;
    private Circle                mainCircle;
    private Pane                  pane;
    private GradientLookup        gradientLookup;
    private ConicalGradient       gradient;
    private Color                 color;
    private ObjectProperty<Color> selectedColor;


    // ******************** Constructors **************************************
    public ColorSelector() {
        this(new Stop(0.000, Color.rgb(255,   0,   0)),
             new Stop(0.125, Color.rgb(255, 127,   0)),
             new Stop(0.250, Color.rgb(255, 255,   0)),
             new Stop(0.375, Color.rgb(127, 255,   0)),
             new Stop(0.500, Color.rgb(  0, 255,   0)),
             new Stop(0.625, Color.rgb(  0, 255, 255)),
             new Stop(0.750, Color.rgb(  0,   0, 255)),
             new Stop(0.875, Color.rgb(255,   0, 255)),
             new Stop(1.000, Color.rgb(255,   0,   0)));
    }
    public ColorSelector(final Stop... STOPS) {
        gradientLookup = new GradientLookup(STOPS);
        selectedColor  = new ObjectPropertyBase<Color>(Color.BLACK) {
            @Override public void set(final Color COLOR) {
                super.set(null == COLOR ? Color.BLACK : COLOR);
                redraw(get());
            }
            @Override public Object getBean() { return ColorSelector.this; }
            @Override public String getName() { return "selectecColor"; }
        };
        color          = Color.BLACK;
        scaleFactor    = 1d;
        init();
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 ||
            Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }
        if (Double.compare(getMinWidth(), 0.0) <= 0 || Double.compare(getMinHeight(), 0.0) <= 0) {
            setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }
        if (Double.compare(getMaxWidth(), 0.0) <= 0 || Double.compare(getMaxHeight(), 0.0) <= 0) {
            setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {
        gradient = new ConicalGradient(PREFERRED_WIDTH * 0.5, PREFERRED_HEIGHT * 0.5, gradientLookup.getStops());

        double center = PREFERRED_WIDTH * 0.5;
        ring = Shape.subtract(new Circle(center, center, center),
                              new Circle(center, center, PREFERRED_WIDTH * 0.28813559));
        ring.setFill(gradient.getImagePattern(ring.getLayoutBounds()));

        mainCircle = new Circle();
        mainCircle.setStrokeType(StrokeType.INSIDE);
        mainCircle.setMouseTransparent(true);

        pane = new Pane(ring, mainCircle);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        widthProperty().addListener(o -> resize());
        heightProperty().addListener(o -> resize());
        ring.setOnMousePressed(e -> selectColor(e));
        ring.setOnMouseDragged(e -> selectColor(e));
        ring.setOnMouseReleased(e -> { if (isDisabled()) return; setSelectedColor(color); });
        disabledProperty().addListener(o -> setOpacity(isDisabled() ? 0.4 : 1.0));
    }


    // ******************** Methods *******************************************
    public Color getSelectedColor() { return selectedColor.get(); }
    public void setSelectedColor(final Color COLOR) { selectedColor.set(COLOR); }
    public ObjectProperty<Color> selectedColorProperty() { return selectedColor; }

    public void redraw(final Color COLOR) {
        mainCircle.setFill(new RadialGradient(0, 0, (size * 0.5), (size * 0.5), (size * 0.15410959),
                                              false, CycleMethod.NO_CYCLE,
                                              new Stop(0.0, COLOR.brighter()),
                                              new Stop(1.0, COLOR)));
        mainCircle.setStroke(COLOR.darker());
    }

    private void selectColor(final MouseEvent EVENT) {
        double  x   = EVENT.getX() - (0.5 * size / scaleFactor);
        double  y   = EVENT.getY() - (0.5 * size / scaleFactor);
        double  deg = Math.toDegrees(Math.atan2(y, x)) + 90;

        deg   = Double.compare(deg, 0d) >= 0 ? deg : (359d + deg);
        color = gradientLookup.getColorAt(deg / 359d);
        redraw(color);
    }


    // ******************** Resizing ******************************************
    private void resize() {
        double width  = getWidth() - getInsets().getLeft() - getInsets().getRight();
        double height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        size   = width < height ? width : height;

        if (width > 0 && height > 0) {
            pane.setMaxSize(size, size);
            pane.relocate((getWidth() - size) * 0.5, (getHeight() - size) * 0.5);

            double center = size * 0.5;
            scaleFactor = size / PREFERRED_WIDTH;
            ring.getTransforms().setAll(new Scale(scaleFactor, scaleFactor, 0, 0));

            mainCircle.setRadius(size * 0.21016949);
            mainCircle.setCenterX(center); mainCircle.setCenterY(center);
        }
    }
}
