package be.webtechie.pi4jgpio.helper;

import be.webtechie.pi4jgpio.characters.AsciiCharacter;
import com.pi4j.io.spi.SpiDevice;

public class AsciiCharacterMode {

    /**
     * Show all the characters as defined in the alphabet enum.
     *
     * @param spi SpiDevice
     * @param waitBetween Milliseconds between every AsciiCharacter
     */
    public static void showAllAsciiCharacters(SpiDevice spi, int waitBetween) {
        try {
            for (int ascii = 32; ascii <= 126; ascii++) {
                AsciiCharacter asciiCharacter = AsciiCharacter.getByAscii(ascii);
                if (asciiCharacter != null) {
                    showAsciiCharacter(spi, asciiCharacter);
                    System.out.println("Written to SPI : " + asciiCharacter.name());
                    Thread.sleep(waitBetween);
                }
            }
        } catch (Exception ex) {
            System.err.println("Error during Ascii: " + ex.getMessage());
        }
    }

    /**
     * Output the given alphabet character to the display.
     *
     * @param spi SpiDevice
     * @param asciiCharacter AsciiCharacter to be shown
     */
    public static void showAsciiCharacter(SpiDevice spi, AsciiCharacter asciiCharacter) {
        try {
            for (int row = 0; row < 8; row++) {
                spi.write((byte) (row + 1), asciiCharacter.getRows().get(row));
            }
        } catch (Exception ex) {
            System.err.println("Error during images: " + ex.getMessage());
        }
    }

    /**
     * Show all the characters as defined in the alphabet enum.
     *
     * @param spi SpiDevice
     * @param waitBetweenMove Milliseconds between every column move
     */
    public static void scrollAllAsciiCharacters(SpiDevice spi, int waitBetweenMove) {
        try {
            for (int ascii = 32; ascii <= 126; ascii++) {
                AsciiCharacter asciiCharacter = AsciiCharacter.getByAscii(ascii);
                if (asciiCharacter != null) {
                    scrollAsciiCharacter(spi, asciiCharacter, waitBetweenMove);
                    System.out.println("Scrolled : " + asciiCharacter.name());
                    Thread.sleep(250);
                }
            }
        } catch (Exception ex) {
            System.err.println("Error during Ascii: " + ex.getMessage());
        }
    }

    /**
     * Scroll a character over the screen.
     *
     * @param spi SpiDevice
     * @param asciiCharacter AsciiCharacter to be scrolled
     * @param waitBetweenMove Milliseconds between every column move
     */
    public static void scrollAsciiCharacter(SpiDevice spi, AsciiCharacter asciiCharacter, int waitBetweenMove) {
        try {
            for (int move = 0; move < (8 * 2); move++) {
                for (int row = 0; row < 8; row++) {
                    int rowValue = 0xFF & asciiCharacter.getRows().get(row);
                    if (move < 8) {
                        rowValue = 0xFF & (rowValue >> (8 - move));
                    } else {
                        rowValue = 0xFF & (rowValue << (move - 8));
                    }
                    spi.write((byte) (row + 1), (byte) rowValue);
                }
                Thread.sleep(waitBetweenMove);
            }
        } catch (Exception ex) {
            System.err.println("Error during images: " + ex.getMessage());
        }
    }
}
