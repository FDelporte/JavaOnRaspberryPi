package be.webtechie.pi4jgpio.helper;

import be.webtechie.pi4jgpio.definition.Image;
import com.pi4j.io.spi.SpiDevice;

public class ImageMode {

    /**
     * Show all the images as defined in the enum.
     *
     * @param spi SpiDevice
     * @param waitBetween Number of milliseconds to wait between every image output
     */
    public static void showAllImages(SpiDevice spi, int waitBetween) {
        try {
            for (Image image : Image.values()) {
                showImage(spi, image);
                System.out.println("Showing image " + image.name());
                Thread.sleep(waitBetween);
            }
        } catch (Exception ex) {
            System.err.println("Error during images: " + ex.getMessage());
        }
    }

    /**
     * Output the given image to the matrix.
     *
     * @param spi SpiDevice
     * @param image Image to be shown
     */
    public static void showImage(SpiDevice spi, Image image) {
        try {
            for (int i = 0; i < 8; i++) {
                spi.write((byte) (i + 1), image.getRows().get(i));
            }
        } catch (Exception ex) {
            System.err.println("Error during images: " + ex.getMessage());
        }
    }
}
