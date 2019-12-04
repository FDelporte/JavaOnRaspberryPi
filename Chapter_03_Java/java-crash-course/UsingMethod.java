import java.text.SimpleDateFormat;
import java.util.Date;

public class UsingMethod {
    public static void main (String[] args) {
        System.out.println("2 x Raspberry Pi 4 4Gb, price: " + getTotal(2, 59.95F) + " Euro");

        System.out.println("Current date and time is: " + getNow());
    }

    public static float getTotal(int quantity, float price) {
        return quantity * price;
    }

    public static String getNow() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
    }
}
