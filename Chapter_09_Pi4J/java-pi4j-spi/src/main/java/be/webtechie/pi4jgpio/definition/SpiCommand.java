package be.webtechie.pi4jgpio.definition;

/**
 * Reserved byte values to send a command.
 */
public enum SpiCommand {
    DECODE_MODE((byte) 0x09),
    BRIGHTNESS((byte) 0x0A),
    SCAN_LIMIT((byte) 0x0B),
    SHUTDOWN_MODE((byte) 0x0C),
    TEST((byte) 0x0F);

    private final byte value;

    SpiCommand(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
