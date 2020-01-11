package be.webtechie.pi4jgpio;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/I2CExample.java
 */
public class App {

    // TSL2561 I2C address
    public static final int TSL2561_ADDR = 0x39; // address pin not connected (FLOATING)
    //public static final int TSL2561_ADDR = 0x29; // address pin connect to GND
    //public static final int TSL2561_ADDR = 0x49; // address pin connected to VDD

    // TSL2561 registers
    public static final byte TSL2561_REG_ID = (byte) 0x8A;
    public static final byte TSL2561_REG_DATA_0 = (byte) 0x8C;
    public static final byte TSL2561_REG_DATA_1 = (byte) 0x8E;
    public static final byte TSL2561_REG_CONTROL = (byte) 0x80;

    // TSL2561 power control values
    public static final byte TSL2561_POWER_UP = (byte) 0x03;
    public static final byte TSL2561_POWER_DOWN = (byte) 0x00;

    public static void main(String[] args) {
        System.out.println("Starting I²C example...");

        // fetch all available busses
        try {
            int[] ids = I2CFactory.getBusIds();
            System.out.println("Found follow I2C busses: " + Arrays.toString(ids));
        } catch (IOException exception) {
            System.out.println("I/O error during fetch of I2C busses occurred");
        }

        try {
            // find available busses
            for (int number = I2CBus.BUS_0; number <= I2CBus.BUS_17; ++number) {
                try {
                    @SuppressWarnings("unused")
                    I2CBus bus = I2CFactory.getInstance(number);
                    System.out.println("Supported I2C bus " + number + " found");
                } catch (IOException exception) {
                    System.out.println("I/O error on I2C bus " + number + " occurred");
                } catch (UnsupportedBusNumberException exception) {
                    System.out.println("Unsupported I2C bus " + number + " required");
                }
            }

            // get the I2C bus to communicate on
            I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);

            // create an I2C device for an individual device on the bus that you want to communicate with
            // in this example we will use the default address for the TSL2561 chip which is 0x39.
            I2CDevice device = i2c.getDevice(TSL2561_ADDR);

            // next, lets perform am I2C READ operation to the TSL2561 chip
            // we will read the 'ID' register from the chip to get its part number and silicon revision number
            System.out.println("... reading ID register from TSL2561");
            int response = device.read(TSL2561_REG_ID);
            System.out.println("TSL2561 ID = " + String.format("0x%02x", response) + " (should be 0x50)");

            // next we want to start taking light measurements, so we need to power up the sensor
            System.out.println("... powering up TSL2561");
            device.write(TSL2561_REG_CONTROL, TSL2561_POWER_UP);

            // wait while the chip collects data
            Thread.sleep(500);

            // now we will perform our first I2C READ operation to retrieve raw integration
            // results from DATA_0 and DATA_1 registers
            System.out.println("... reading DATA registers from TSL2561");
            int data0 = device.read(TSL2561_REG_DATA_0);
            int data1 = device.read(TSL2561_REG_DATA_1);

            // print raw integration results from DATA_0 and DATA_1 registers
            System.out.println("TSL2561 DATA 0 = " + String.format("0x%02x", data0));
            System.out.println("TSL2561 DATA 1 = " + String.format("0x%02x", data1));

            // before we exit, lets not forget to power down light sensor
            System.out.println("... powering down TSL2561");
            device.write(TSL2561_REG_CONTROL, TSL2561_POWER_DOWN);

            System.out.println("Done");
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
