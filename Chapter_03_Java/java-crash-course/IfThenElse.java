public class IfThenElse {
    public static void main (String[] args) {
        // Compare integer value
        int piHeaderVersion = 1;

        if (piHeaderVersion == 1) {
            System.out.println("Header version 1 is used on original Model B");
        } else if (piHeaderVersion == 2) {
            System.out.println("Header version 2 is used on Model A and Model B (revision 2)");
        } else if (piHeaderVersion == 3) {
            System.out.println("Header version 3 is used on Model A+, B+, Pi Zero, Pi Zero W, Pi2B, Pi3B, Pi4B");
        } else {
            System.out.println("Sorry, header version " + piHeaderVersion + " is not known");
        }

        // Compare strings
        String string1 = "Hello world";
        String string2 = "Hello" + " " + "world";
        String string3 = "Hello World";

        System.out.println("Are string1 and string2 equal? " + string1.equals(string2));
        System.out.println("Are string1 and string3 equal? " + string1.equals(string3));
        System.out.println("Are string1 and string3 equal ignoring the case? " + string1.equalsIgnoreCase(string3));

        if (string1.equalsIgnoreCase(string3)) {
            System.out.println("string1 and string3 are equal ignoring the case");
        }
    }
}
