package be.webtechie.pi4jgpio;

import be.webtechie.pi4jgpio.listener.SerialListener;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPort;
import com.pi4j.io.serial.StopBits;
import java.io.IOException;
import java.util.Date;

/**
 * Based on https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/SerialExample.java
 */
public class App {

    /**
     * This example program supports the following optional command arguments/options
     * <ul>
     *   <li>"--device (device-path)"                   [DEFAULT: /dev/ttyAMA0]</li>
     *   <li>"--baud (baud-rate)"                       [DEFAULT: 38400]</li>
     *   <li>"--data-bits (5|6|7|8)"                    [DEFAULT: 8]</li>
     *   <li>"--parity (none|odd|even)"                 [DEFAULT: none]</li>
     *   <li>"--stop-bits (1|2)"                        [DEFAULT: 1]</li>
     *   <li>"--flow-control (none|hardware|software)"  [DEFAULT: none]</li>
     * </ul>
     */
    public static void main( String[] args ) {
        System.out.println("Starting Serial communication example...");

        // !! ATTENTION !!
        // By default, the serial port is configured as a console port
        // for interacting with the Linux OS shell.  If you want to use
        // the serial port in a software program, you must disable the
        // OS from using this port.
        //
        // Please see this blog article for instructions on how to disable
        // the OS console for this port:
        // https://www.cube-controls.com/2015/11/02/disable-serial-port-terminal-output-on-raspbian/

        // Create an instance of the serial communications class
        final Serial serial = SerialFactory.createInstance();

        // Create and register the serial data listener
        serial.addListener(new SerialListener());

        try {
            // Create serial config object
            SerialConfig config = new SerialConfig();

            // Set default serial settings (device, baud rate, flow control, etc)
            //
            // by default, use the DEFAULT com port on the Raspberry Pi (exposed on GPIO header)
            // NOTE: this utility method will determine the default serial port for the
            //       detected platform and board/model.  For all Raspberry Pi models
            //       except the 3B, it will return "/dev/ttyAMA0".  For Raspberry Pi
            //       model 3B may return "/dev/ttyS0" or "/dev/ttyAMA0" depending on
            //       environment configuration.
            config.device(SerialPort.getDefaultPort())
                    .baud(Baud._38400)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1)
                    .flowControl(FlowControl.NONE);

            // Display connection details
            System.out.println(" Connecting to: " + config.toString());
            System.out.println(" We are sending ASCII data on the serial port every 1 second.");
            System.out.println(" Data received on serial port will be displayed below.");

            // Open the default serial device/port with the configuration settings
            serial.open(config);

            // Continuous loop to keep the program running
            while(true) {
                try {
                    // Write a formatted string to the serial transmit buffer
                    serial.write("CURRENT TIME: " + new Date().toString());

                    // Write a individual bytes to the serial transmit buffer
                    serial.write((byte) 13);
                    serial.write((byte) 10);

                    // Write a simple string to the serial transmit buffer
                    serial.write("Second Line");

                    // Write a individual characters to the serial transmit buffer
                    serial.write('\r');
                    serial.write('\n');

                    // Write a string terminating with CR+LF to the serial transmit buffer
                    serial.writeln("Third Line");
                }
                catch(IllegalStateException ex){
                    ex.printStackTrace();
                }

                // Wait 1 second before continuing
                Thread.sleep(1000);
            }
        } catch(IOException ex) {
            System.err.println("Error during serial communication initialization : " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.err.println("Error during thread handling: " + ex.getMessage());
        }
    }
}
