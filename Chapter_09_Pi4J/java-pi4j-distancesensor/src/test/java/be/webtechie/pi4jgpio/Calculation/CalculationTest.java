package be.webtechie.pi4jgpio.Calculation;

import static org.junit.Assert.assertEquals;

import be.webtechie.pi4jgpio.helper.Calculation;
import org.junit.Test;

public class CalculationTest {

    @Test
    public void testDistanceZero() {
        assertEquals(0, Calculation.getDistance(0, false));
    }

    @Test
    public void testDistanceZeroHalf() {
        assertEquals(0, Calculation.getDistance(0, true));
    }

    @Test
    public void testDistanceOne() {
        assertEquals(34300, Calculation.getDistance(1, false));
    }

    @Test
    public void testDistanceOneHalf() {
        assertEquals(34300 / 2, Calculation.getDistance(1, true));
    }

    @Test
    public void testDifferenceOneSecond() {
        long now = System.nanoTime();
        // Compare two floats, which in this case need to match with a precision (delta) 1
        assertEquals(1F, Calculation.getSecondsDifference(now, now + 1000000000), 1);
    }
}
