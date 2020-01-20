package eu.hansolo.fx.colorselector;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by hansolo on 21.12.15.
 */
public class GradientLookup {
    private Map<Double, Stop> stops;


    // ******************** Constructors **************************************
    public GradientLookup () {
        this(new Stop[]{});
    }
    public GradientLookup(final Stop... STOPS) {
        stops = new TreeMap<>();
        for (Stop stop : STOPS) { stops.put(stop.getOffset(), stop); }
        init();
    }


    // ******************** Initialization ************************************
    private void init() {
        if (stops.isEmpty()) return;

        double minFraction = Collections.min(stops.keySet());
        double maxFraction = Collections.max(stops.keySet());

        if (Double.compare(minFraction, 0d) > 0) { stops.put(0.0, new Stop(0.0, stops.get(minFraction).getColor())); }
        if (Double.compare(maxFraction, 1d) < 0) { stops.put(1.0, new Stop(1.0, stops.get(maxFraction).getColor())); }
    }


    // ******************** Methods *******************************************
    public Color getColorAt(final double POSITION_OF_COLOR) {
        if (stops.isEmpty()) return Color.BLACK;

        final double POSITION = clamp(0d, 1d, POSITION_OF_COLOR);
        final Color COLOR;
        if (stops.size() == 1) {
            final Map<Double, Color> ONE_ENTRY = (Map<Double, Color>) stops.entrySet().iterator().next();
            COLOR = stops.get(ONE_ENTRY.keySet().iterator().next()).getColor();
        } else {
            Stop lowerBound = stops.get(0.0);
            Stop upperBound = stops.get(1.0);
            for (Double fraction : stops.keySet()) {
                if (Double.compare(fraction,POSITION) < 0) {
                    lowerBound = stops.get(fraction);
                }
                if (Double.compare(fraction, POSITION) > 0) {
                    upperBound = stops.get(fraction);
                    break;
                }
            }
            COLOR = interpolateColor(lowerBound, upperBound, POSITION);
        }
        return COLOR;
    }

    public List<Stop> getStops() { return new ArrayList<>(stops.values()); }
    public void setStops(final Stop... STOPS) { setStops(Arrays.asList(STOPS)); }
    public void setStops(final List<Stop> STOPS) {
        stops.clear();
        for (Stop stop : STOPS) { stops.put(stop.getOffset(), stop); }
        init();
    }

    private static final <T extends Number> T clamp(final T MIN, final T MAX, final T VALUE) {
        if (VALUE.doubleValue() < MIN.doubleValue()) return MIN;
        if (VALUE.doubleValue() > MAX.doubleValue()) return MAX;
        return VALUE;
    }

    private Color interpolateColor(final Stop LOWER_BOUND, final Stop UPPER_BOUND, final double POSITION) {
        final double POS  = (POSITION - LOWER_BOUND.getOffset()) / (UPPER_BOUND.getOffset() - LOWER_BOUND.getOffset());

        final double DELTA_RED     = (UPPER_BOUND.getColor().getRed()     - LOWER_BOUND.getColor().getRed())     * POS;
        final double DELTA_GREEN   = (UPPER_BOUND.getColor().getGreen()   - LOWER_BOUND.getColor().getGreen())   * POS;
        final double DELTA_BLUE    = (UPPER_BOUND.getColor().getBlue()    - LOWER_BOUND.getColor().getBlue())    * POS;
        final double DELTA_OPACITY = (UPPER_BOUND.getColor().getOpacity() - LOWER_BOUND.getColor().getOpacity()) * POS;

        double red     = clamp(0d, 1d, (LOWER_BOUND.getColor().getRed()     + DELTA_RED));
        double green   = clamp(0d, 1d, (LOWER_BOUND.getColor().getGreen()   + DELTA_GREEN));
        double blue    = clamp(0d, 1d, (LOWER_BOUND.getColor().getBlue()    + DELTA_BLUE));
        double opacity = clamp(0d, 1d, (LOWER_BOUND.getColor().getOpacity() + DELTA_OPACITY));

        return Color.color(red, green, blue, opacity);
    }
}
