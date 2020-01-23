package be.webtechie.i2c.definition;

public enum State {
    RELAY_ON((byte) 0xFF),
    RELAY_OFF((byte) 0x00);

    private final byte value;

    State(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return this.value;
    }
}
