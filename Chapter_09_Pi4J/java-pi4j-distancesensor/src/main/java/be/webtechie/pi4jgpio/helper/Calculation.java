package be.webtechie.pi4jgpio.helper;

import java.util.concurrent.TimeUnit;

/**
 * Helper class for duration and distance calculation.
 */
public class Calculation {
    /**
     * Get the distance (in cm) for a given duration.
     * The calculation is based on the speed of sound which is 34300 cm/s.
     *
     * @param seconds Number of seconds
     * @param half Flag to define if the calculated distance must be divided
     */
    public static int getDistance(float seconds, boolean half) {
        float distance = seconds * 34300;
        return Math.round(half ? distance / 2 : distance);
    }

    /**
     * Get the number of seconds between two nanosecond timestamps.
     * 1 second = 1000000000 nanoseconds
     *
     * @param start Start timestamp in nanoseconds
     * @param end End timestamp in nanoseconds
     */
    public static float getSecondsDifference(long start, long end) {
        return (end - start) / 1000000000F;
    }
}
