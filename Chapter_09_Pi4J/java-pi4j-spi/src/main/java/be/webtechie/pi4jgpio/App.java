package be.webtechie.pi4jgpio;

import be.webtechie.pi4jgpio.helper.AsciiCharacterMode;
import be.webtechie.pi4jgpio.helper.DemoMode;
import be.webtechie.pi4jgpio.helper.ImageMode;
import be.webtechie.pi4jgpio.helper.SpiCommand;
import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/SpiExample.java
 */
public class App {

    public static void main(String args[]) {
        try {
            System.out.println("Starting SPI example...");

            // Initialize the SpiFactory
            SpiDevice spi = SpiFactory.getInstance(SpiChannel.CS0,
                    SpiDevice.DEFAULT_SPI_SPEED, // default spi speed 1 MHz
                    SpiDevice.DEFAULT_SPI_MODE); // default spi mode 0

            spi.write(SpiCommand.DECODE_MODE.getValue(), (byte) 0x00);
            System.out.println("Disabled BCD mode");

            DemoMode.allOn(spi);
            Thread.sleep(1000);
            DemoMode.allOff(spi);
            Thread.sleep(1000);

            DemoMode.showRows(spi, 250);
            DemoMode.showCols(spi, 250);
            DemoMode.showRandomOutput(spi, 5, 500);

            ImageMode.showAllImages(spi, 2000);
            AsciiCharacterMode.showAllAsciiCharacters(spi, 750);
            AsciiCharacterMode.scrollAllAsciiCharacters(spi, 50);
        } catch (Exception ex) {
            System.err.println("Error in main function: " + ex.getMessage());
        }
    }
}
