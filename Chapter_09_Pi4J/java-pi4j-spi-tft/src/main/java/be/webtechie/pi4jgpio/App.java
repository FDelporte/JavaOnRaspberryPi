package be.webtechie.pi4jgpio;

import be.webtechie.pi4jgpio.helper.AsciiCharacterMode;
import be.webtechie.pi4jgpio.helper.DemoMode;
import be.webtechie.pi4jgpio.helper.ImageMode;
import be.webtechie.pi4jgpio.definition.SpiCommand;
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

            spi.write(SpiCommand.TEST.getValue(), (byte) 0x01);
            System.out.println("Test mode all on");
            Thread.sleep(1000);

            spi.write(SpiCommand.TEST.getValue(), (byte) 0x00);
            System.out.println("Test mode all off");
            Thread.sleep(1000);
            
            spi.write(SpiCommand.DECODE_MODE.getValue(), (byte) 0x00);
            System.out.println("Use all bits");

            spi.write(SpiCommand.BRIGHTNESS.getValue(), (byte) 0x08);
            System.out.println("Changed brightness to medium level"
                + " (0x00 lowest, 0x0F highest)");

            spi.write(SpiCommand.SCAN_LIMIT.getValue(), (byte) 0x0f);
            System.out.println("Configured to scan all digits");
            
            spi.write(SpiCommand.SHUTDOWN_MODE.getValue(), (byte) 0x01);
            System.out.println("Woke up the MAX7219, is off on startup");

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
