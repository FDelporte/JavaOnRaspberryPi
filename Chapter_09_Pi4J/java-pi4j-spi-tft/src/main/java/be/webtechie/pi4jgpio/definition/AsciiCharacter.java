package be.webtechie.pi4jgpio.definition;

import java.util.Arrays;
import java.util.List;

public enum AsciiCharacter {
    SPACE(0x20, 2, Arrays.asList(
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2)
    )),
    A(0x41, 6, Arrays.asList(
            (byte) Integer.parseInt("00110000", 2),
            (byte) Integer.parseInt("00110000", 2),
            (byte) Integer.parseInt("01001000", 2),
            (byte) Integer.parseInt("01001000", 2),
            (byte) Integer.parseInt("01111000", 2),
            (byte) Integer.parseInt("11111100", 2),
            (byte) Integer.parseInt("10000100", 2),
            (byte) Integer.parseInt("10000100", 2)
    )),
    B(0x42, 6, Arrays.asList(
            (byte) Integer.parseInt("11111000", 2),
            (byte) Integer.parseInt("10000100", 2),
            (byte) Integer.parseInt("10000100", 2),
            (byte) Integer.parseInt("11111000", 2),
            (byte) Integer.parseInt("10000100", 2),
            (byte) Integer.parseInt("10000100", 2),
            (byte) Integer.parseInt("10000100", 2),
            (byte) Integer.parseInt("11111000", 2)
    )),
    E(0x45, 6, Arrays.asList(
            (byte) Integer.parseInt("11111100", 2),
            (byte) Integer.parseInt("10000000", 2),
            (byte) Integer.parseInt("10000000", 2),
            (byte) Integer.parseInt("11111100", 2),
            (byte) Integer.parseInt("10000000", 2),
            (byte) Integer.parseInt("10000000", 2),
            (byte) Integer.parseInt("10000000", 2),
            (byte) Integer.parseInt("11111100", 2)
    )),
    S(0x53, 6, Arrays.asList(
            (byte) Integer.parseInt("01111000", 2),
            (byte) Integer.parseInt("10000100", 2),
            (byte) Integer.parseInt("10000000", 2),
            (byte) Integer.parseInt("01111000", 2),
            (byte) Integer.parseInt("00000100", 2),
            (byte) Integer.parseInt("00000100", 2),
            (byte) Integer.parseInt("10000100", 2),
            (byte) Integer.parseInt("01111000", 2)
    )),
    T(0x54, 5, Arrays.asList(
            (byte) Integer.parseInt("11111000", 2),
            (byte) Integer.parseInt("00100000", 2),
            (byte) Integer.parseInt("00100000", 2),
            (byte) Integer.parseInt("00100000", 2),
            (byte) Integer.parseInt("00100000", 2),
            (byte) Integer.parseInt("00100000", 2),
            (byte) Integer.parseInt("00100000", 2),
            (byte) Integer.parseInt("00100000", 2)
    ));

    private final int ascii;
    private final int numberOfColumns;
    private final List<Byte> rows;

    AsciiCharacter(int ascii, int numberOfColumns, List<Byte> rows) {
        this.ascii = ascii;
        this.numberOfColumns = numberOfColumns;
        this.rows = rows;
    }

    public int getAscii() {
        return ascii;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public List<Byte> getRows() {
        return rows;
    }

    public static AsciiCharacter getByAscii(int ascii) {
        for (AsciiCharacter asciiCharacter : AsciiCharacter.values()) {
            if (asciiCharacter.getAscii() == ascii) {
                return asciiCharacter;
            }
        }
        return null;
    }

    public static AsciiCharacter getByChar(char character) {
        return getByAscii(character);
    }
}
