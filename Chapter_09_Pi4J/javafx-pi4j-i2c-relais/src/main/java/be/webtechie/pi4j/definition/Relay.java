package be.webtechie.pi4j.definition;

public enum Relay {
    RELAY_1((byte) 0x01),
    RELAY_2((byte) 0x02),
    RELAY_3((byte) 0x03),
    RELAY_4((byte) 0x04);

    private final byte channel;

    Relay(byte channel) {
        this.channel = channel;
    }

    public byte getChannel() {
        return this.channel;
    }
}
