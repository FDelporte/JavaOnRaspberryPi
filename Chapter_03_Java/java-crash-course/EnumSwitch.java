public class EnumSwitch {
    public static void main (String[] args) {
        // Compare integer value
        HEADER_VERSION piHeaderVersion = HEADER_VERSION.TYPE_3;

        switch(piHeaderVersion) {
            case TYPE_1:
                System.out.println("Header version 1 is used on original Model B");
                return;
            case TYPE_2:
                System.out.println("Header version 2 is used on Model A and Model B (revision 2)");
                return;
            case TYPE_3:
                System.out.println("Header version 3 is used on Model A+, B+, Pi Zero, Pi Zero W, Pi2B, Pi3B, Pi4B");
                return;
            default:
                System.out.println("Sorry, header version " + piHeaderVersion + " is not known");
        }
    }

    enum HEADER_VERSION {
        TYPE_1, TYPE_2, TYPE_3, UNKNOWN;
    }
}
