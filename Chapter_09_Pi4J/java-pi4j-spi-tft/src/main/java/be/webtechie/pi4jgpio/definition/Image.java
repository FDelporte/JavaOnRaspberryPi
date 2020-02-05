package be.webtechie.pi4jgpio.definition;

import java.util.Arrays;
import java.util.List;

public enum Image {
    HEART(Arrays.asList(
            (byte) Integer.parseInt("00100100", 2),
            (byte) Integer.parseInt("01111110", 2),
            (byte) Integer.parseInt("11111111", 2),
            (byte) Integer.parseInt("11111111", 2),
            (byte) Integer.parseInt("01111110", 2),
            (byte) Integer.parseInt("00111100", 2),
            (byte) Integer.parseInt("00111100", 2),
            (byte) Integer.parseInt("00011000", 2)
    )),
    PI_LOGO(Arrays.asList(
            (byte) Integer.parseInt("01100110", 2),
            (byte) Integer.parseInt("00111100", 2),
            (byte) Integer.parseInt("01011010", 2),
            (byte) Integer.parseInt("01011010", 2),
            (byte) Integer.parseInt("10100101", 2),
            (byte) Integer.parseInt("10100101", 2),
            (byte) Integer.parseInt("01011010", 2),
            (byte) Integer.parseInt("00111100", 2)
    )),
    SMILEY(Arrays.asList(
            (byte) Integer.parseInt("00011000", 2),
            (byte) Integer.parseInt("01100110", 2),
            (byte) Integer.parseInt("10000001", 2),
            (byte) Integer.parseInt("10100101", 2),
            (byte) Integer.parseInt("10000001", 2),
            (byte) Integer.parseInt("10011001", 2),
            (byte) Integer.parseInt("01100110", 2),
            (byte) Integer.parseInt("00011000", 2)
    )),
    ARROW_LEFT(Arrays.asList(
            (byte) Integer.parseInt("00000011", 2),
            (byte) Integer.parseInt("00001100", 2),
            (byte) Integer.parseInt("00110000", 2),
            (byte) Integer.parseInt("11000000", 2),
            (byte) Integer.parseInt("11000000", 2),
            (byte) Integer.parseInt("00110000", 2),
            (byte) Integer.parseInt("00001100", 2),
            (byte) Integer.parseInt("00000011", 2)
    )),
    CROSS(Arrays.asList(
            (byte) Integer.parseInt("10000001", 2),
            (byte) Integer.parseInt("01000010", 2),
            (byte) Integer.parseInt("00100100", 2),
            (byte) Integer.parseInt("00011000", 2),
            (byte) Integer.parseInt("00011000", 2),
            (byte) Integer.parseInt("00100100", 2),
            (byte) Integer.parseInt("01000010", 2),
            (byte) Integer.parseInt("10000001", 2)
    ));

    private final List<Byte> rows;

    Image(List<Byte> rows) {
        this.rows = rows;
    }

    public List<Byte> getRows() {
        return rows;
    }
}