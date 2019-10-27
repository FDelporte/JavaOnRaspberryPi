class HexIntegerToString {
    public static void main(String[] args) {
        convertByte((byte) 8);
        convertByte((byte) 124);
        convertByte((byte) 170);

        convertInteger(0x7FFFFFFF);
        convertInteger(0x80000001);
    }

    private static void convertByte(byte value) {        
        System.out.println("Byte Unsigned: " + Byte.toUnsignedInt(value) + "\tSigned: " + value);
        System.out.println("    Hex value:  0x" + Integer.toHexString(value & 0xFF));
        System.out.println("    Binair:     " + padLeftZero(Integer.toBinaryString(value & 0xFF), 8));
        
    }

    private static void convertInteger(int value) {        
        System.out.println("Integer Unsigned: " + Integer.toUnsignedString(value) + "\tSigned: " + Integer.toString(value));
        System.out.println("    Hex value:  0x" + Integer.toHexString(value));
        System.out.println("    Binair:     " + padLeftZero(Integer.toBinaryString(value), 32));
    }

    private static String padLeftZero(String txt, int length) {
        StringBuilder rt = new StringBuilder();
        for (int i = 0; i < (length - txt.length()); i++) {
            rt.append("0");
        }
        return rt.append(txt).toString();
    }
}