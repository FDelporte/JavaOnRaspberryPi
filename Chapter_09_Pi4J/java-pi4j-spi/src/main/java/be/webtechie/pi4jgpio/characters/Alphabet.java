package be.webtechie.pi4jgpio.characters;

import java.util.Arrays;
import java.util.List;

public enum Alphabet {
    SPACE(0x20, Arrays.asList(
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2),
            (byte) Integer.parseInt("00", 2)
    )),
    A(0x41, Arrays.asList(
            (byte) Integer.parseInt("001100", 2),
            (byte) Integer.parseInt("001100", 2),
            (byte) Integer.parseInt("010010", 2),
            (byte) Integer.parseInt("010010", 2),
            (byte) Integer.parseInt("011110", 2),
            (byte) Integer.parseInt("111111", 2),
            (byte) Integer.parseInt("100001", 2),
            (byte) Integer.parseInt("100001", 2)
    )),
    B(0x42, Arrays.asList(
            (byte) Integer.parseInt("111110", 2),
            (byte) Integer.parseInt("100001", 2),
            (byte) Integer.parseInt("100001", 2),
            (byte) Integer.parseInt("111110", 2),
            (byte) Integer.parseInt("100001", 2),
            (byte) Integer.parseInt("100001", 2),
            (byte) Integer.parseInt("100001", 2),
            (byte) Integer.parseInt("111110", 2)
    ));

    private final int ascii;
    private final List<Byte> rows;

    Alphabet(int ascii, List<Byte> rows) {
        this.ascii = ascii;
        this.rows = rows;
    }

    public List<Byte> getRows() {
        return rows;
    }

    public int getAscii() {
        return ascii;
    }

    public static Alphabet getByAscii(int ascii) {
        for (Alphabet alphabet : Alphabet.values()) {
            if (alphabet.getAscii() == ascii) {
                return alphabet;
            }
        }
        return null;
    }
}
