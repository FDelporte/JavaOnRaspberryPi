package be.webtechie.pi4jgpio.helper;

import be.webtechie.pi4jgpio.definition.SpiCommand;
import com.pi4j.io.spi.SpiDevice;
import java.util.Random;

public class DemoMode {

    /**
     * Highlight all rows one by one.
     *
     * @param spi SpiDevice
     * @param waitBetween Number of milliseconds to wait between every row output
     */
    public static void showRows(SpiDevice spi, int waitBetween) {
        try {
            for (int onRow = 1; onRow <= 8; onRow++) {
                for (int row = 1; row <= 8; row++) {
                    spi.write((byte) row, (onRow == row ? (byte) 0xff : (byte) 0x00));
                }
                System.out.println("Row " + onRow + " is on");
                Thread.sleep(waitBetween);
            }
        } catch (Exception ex) {
            System.err.println("Error during row demo: " + ex.getMessage());
        }
    }

    /**
     * Highlight all columns one by one.
     *
     * @param spi SpiDevice
     * @param waitBetween Number of milliseconds to wait between every column output
     */
    public static void showCols(SpiDevice spi, int waitBetween) {
        try {
            for (int onColumn = 0; onColumn < 8; onColumn++) {
                for (int row = 1; row <= 8; row++) {
                    spi.write((byte) row, (byte) (1 << (8 - onColumn)));
                }
                System.out.println("Col " + onColumn + " is on");
                Thread.sleep(waitBetween);
            }
        } catch (Exception ex) {
            System.err.println("Error during column demo: " + ex.getMessage());
        }
    }

    /**
     * Demo mode which generates specified number of cycles of random enabled LEDs.
     *
     * @param spi SpiDevice
     * @param numberOfLoops Number of random outputs to be generated
     * @param waitBetween Number of milliseconds to wait between random screens
     */
    public static void showRandomOutput(SpiDevice spi, int numberOfLoops, int waitBetween) {
        try {
            Random r = new Random();
            int min = 0;
            int max = 255;

            for (int loop = 1; loop <= numberOfLoops; loop++) {
                for (int row = 1; row <= 8; row++) {
                    spi.write((byte) row, (byte) (r.nextInt((max - min) + 1) + min));
                }
                System.out.println("Random effect " + loop);
                Thread.sleep(waitBetween);
            }
        } catch (Exception ex) {
            System.err.println("Error during random demo: " + ex.getMessage());
        }
    }
}
