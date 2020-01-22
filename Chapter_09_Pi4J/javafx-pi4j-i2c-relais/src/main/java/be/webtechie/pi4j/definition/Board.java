package be.webtechie.pi4j.definition;

public enum Board {
    BOARD_1(0x10),
    BOARD_2(0x11);

    private final int address;

    Board(int address) {
        this.address = address;
    }

    public int getAddress() {
        return this.address;
    }
}
