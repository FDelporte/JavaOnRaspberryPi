package be.webtechie.pi4jgpio.helper;

public enum SpiCommand {
    TEST((byte) 0x0F),
    BRIGHTNESS((byte) 0x0A),
    SCAN_LIMIT((byte) 0x0B),
    DECODE_MODE((byte) 0x09),
    ON_OFF((byte) 0x0C);

    private final byte value;

    SpiCommand(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
