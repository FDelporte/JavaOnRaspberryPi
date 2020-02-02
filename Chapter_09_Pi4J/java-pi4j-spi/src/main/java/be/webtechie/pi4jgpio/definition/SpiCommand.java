package be.webtechie.pi4jgpio.definition;

/**
 * Reserved byte values to send a command.
 */
public enum SpiCommand {
    TEST((byte) 0x0F),
    BRIGHTNESS((byte) 0x0A),
    SCAN_LIMIT((byte) 0x0B),
    DECODE_MODE((byte) 0x09),
    WAKE_UP((byte) 0x0C);

    private final byte value;

    SpiCommand(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
