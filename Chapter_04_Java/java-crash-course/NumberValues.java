public class NumberValues {
    public static void main (String[] args) {
        int intValue = 2;
        float floatValue = 1.12345678901234567890F;
        double doubleValue = 1.12345678901234567890D;

        System.out.println("Integer: " + intValue);
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);

        System.out.println("Multiply: " + (intValue * floatValue) + ", rounded: " + Math.round(intValue * floatValue));
    }
}
