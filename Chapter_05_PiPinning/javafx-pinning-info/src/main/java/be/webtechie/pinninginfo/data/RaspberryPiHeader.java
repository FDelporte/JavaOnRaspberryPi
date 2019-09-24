package be.webtechie.pinninginfo.data;

import java.util.ArrayList;
import java.util.List;

/**
 * List of pins in a Raspberry Pi header.
 */
public class RaspberryPiHeader {
    public static List<HeaderPin> get40PinsHeader() {
        List<HeaderPin> header = new ArrayList<>();

        header.add(new HeaderPin(1, PinType.POWER, null, "3.3 VDC"));
        header.add(new HeaderPin(2, PinType.POWER, null, "5.0 VDC"));
        header.add(new HeaderPin(3, PinType.DIGITAL_NO_PULL_DOWN, 8, "SDA1 (I2C)", "SDA.1 pin has a physical pull-up resistor"));
        header.add(new HeaderPin(4, PinType.POWER, null, "5.0 VDC"));
        header.add(new HeaderPin(5, PinType.DIGITAL_NO_PULL_DOWN, 9, "SCL1 (I2C)", "SCL.1 pin has a physical pull-up resistor"));
        header.add(new HeaderPin(6, PinType.GROUND, null, "Ground"));
        header.add(new HeaderPin(7, PinType.DIGITAL, 7, "GPCLK0"));
        header.add(new HeaderPin(8, PinType.DIGITAL, 15,  "TxD (UART)"));
        header.add(new HeaderPin(9, PinType.GROUND, null, "Ground"));
        header.add(new HeaderPin(10, PinType.DIGITAL, 16, "RxD (UART)"));
        header.add(new HeaderPin(11, PinType.DIGITAL,0, ""));
        header.add(new HeaderPin(12, PinType.DIGITAL_AND_PWM, 1, "PCM_CLK/PWM0", "Supports PWM0 [ALT5]"));
        header.add(new HeaderPin(13, PinType.DIGITAL, 2, ""));
        header.add(new HeaderPin(14, PinType.GROUND, null, "Ground"));
        header.add(new HeaderPin(15, PinType.DIGITAL, 3, ""));
        header.add(new HeaderPin(16, PinType.DIGITAL, 4, ""));
        header.add(new HeaderPin(17, PinType.POWER, null, "3.3 VDC"));
        header.add(new HeaderPin(18, PinType.DIGITAL, 5, ""));
        header.add(new HeaderPin(19, PinType.DIGITAL,12, "MOSI (SPI)"));
        header.add(new HeaderPin(20, PinType.GROUND, null, "Ground"));
        header.add(new HeaderPin(21, PinType.DIGITAL, 13, "MISO (SPI)"));
        header.add(new HeaderPin(22, PinType.DIGITAL, 6, ""));
        header.add(new HeaderPin(23, PinType.DIGITAL, 14, "SCLK (SPI)"));
        header.add(new HeaderPin(24, PinType.DIGITAL, 10, "CE0 (SPI)"));
        header.add(new HeaderPin(25, PinType.GROUND, null, "Ground"));
        header.add(new HeaderPin(26, PinType.DIGITAL, 11, "CE1 (SPI)"));
        header.add(new HeaderPin(27, PinType.DIGITAL_NO_PULL_DOWN, 30, "SDA0 I2C ID EEPROM", "SDA.0 pin has a physical pull-up resistor"));
        header.add(new HeaderPin(28, PinType.DIGITAL_NO_PULL_DOWN, 31, "SCL0 I2C ID EEPROM", "SDC.0 pin has a physical pull-up resistor"));
        header.add(new HeaderPin(29, PinType.DIGITAL, 21, "GPCLK1", "Requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header)"));
        header.add(new HeaderPin(30, PinType.GROUND, null, "Ground"));
        header.add(new HeaderPin(31, PinType.DIGITAL,22, "GPCL2", "Requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header)"));
        header.add(new HeaderPin(32, PinType.DIGITAL_AND_PWM, 26, "PWM0", "Requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header) : supports PWM0 [ALT0]"));
        header.add(new HeaderPin(33, PinType.DIGITAL_AND_PWM, 23, "PWM1", "Requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header) : supports PWM1 [ALT0]"));
        header.add(new HeaderPin(34, PinType.GROUND, null, "Ground"));
        header.add(new HeaderPin(35, PinType.DIGITAL_AND_PWM, 24, "PCM_FS/PWM1", "requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header) : supports PWM1 [ALT5]"));
        header.add(new HeaderPin(36, PinType.DIGITAL, 27, "", "Requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header)"));
        header.add(new HeaderPin(37, PinType.DIGITAL, 25, "", "Requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header)"));
        header.add(new HeaderPin(38, PinType.DIGITAL, 28, "PCM_DIN", "Requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header)"));
        header.add(new HeaderPin(39, PinType.GROUND, null, "Ground"));
        header.add(new HeaderPin(40, PinType.DIGITAL,29, "PCM_DOUT", "Requires 3B, 2B, Zero, A+, B+ or newer model (40 pin header)"));

        return header;
    }
}
