package be.webtechie.javaspringstream.dto;

/**
 * Object containing a distance measurement.
 */
public class DistanceMeasurement {
    private final long timestamp;
    private final int distance;
    private final float duration;

    /**
     * Constructor which will add the current timestamp.
     *
     * @param distance The distance in centimeter
     * @param duration The measurement duration in nanos
     */
    public DistanceMeasurement(int distance, float duration) {
        this.timestamp = System.currentTimeMillis();
        this.distance = distance;
        this.duration = duration;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getDistance() {
        return distance;
    }

    public float getDuration() {
        return duration;
    }
}
