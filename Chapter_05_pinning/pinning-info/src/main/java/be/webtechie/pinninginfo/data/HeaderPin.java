package be.webtechie.pinninginfo.data;

/**
 * Describes a pin in the header.
 */
public class HeaderPin {
    private final int pinNumber;
    private final PinType pinType;
    private final Integer wiringPiNumber;
    private final String name;
    private final String remark;

    public HeaderPin(int pinNumber, PinType pinType, Integer wiringPiNumber, String name) {
        this(pinNumber, pinType, wiringPiNumber, name, "");
    }

    public HeaderPin(int pinNumber, PinType pinType, Integer wiringPiNumber, String name, String remark) {
        this.pinNumber = pinNumber;
        this.pinType = pinType;
        this.wiringPiNumber = wiringPiNumber;
        this.name = name;
        this.remark = remark;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public PinType getPinType() {
        return pinType;
    }

    public Integer getWiringPiNumber() {
        return wiringPiNumber;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }
}
