class HexIntegerToString {
    public static void main(String[] args) {
        convert(0x80000000);
        convert(0x80000001);
    }

    private static void convert(int hex) {
        System.out.println("Hex value: 0x" + Integer.toHexString(hex));
        System.out.println("    Unsigned: " + Integer.toUnsignedString(hex));
        System.out.println("    Signed:   " + Integer.toString(hex));
    }
}