package be.webtechie.javaspringstream.dto;

public class DistanceMeasurement {
    private final long timestamp;
    private final int distance;
    private final float duration;

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
