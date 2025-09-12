package be.webtechie.javaspringstream.dto;

/**
 * Object containing a distance measurement.
 */
public record DistanceMeasurement(
        long timestamp,
        int distance,
        float duration) {
    /**
     * Constructor which will add the current timestamp.
     *
     * @param distance The distance in centimeter
     * @param duration The measurement duration in nanos
     */
    public DistanceMeasurement(int distance, float duration) {
        this(System.currentTimeMillis(), distance, duration);
    }
}