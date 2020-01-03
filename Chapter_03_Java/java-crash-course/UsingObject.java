import java.util.ArrayList;
import java.util.List;

public class UsingObject {
    public static void main (String[] args) {
        List<ShoppingCartItem> items = new ArrayList<>();
        items.add(new ShoppingCartItem("Raspberry Pi 4, 4Gb", 1, 59.95F));
        items.add(new ShoppingCartItem("Micro-HDMI cable", 2, 5.9F));
        items.add(new ShoppingCartItem("Raspberry Pi 4 power supply", 1, 9.95F));

        double total = 0D;
        for (ShoppingCartItem item : items) {
            System.out.println(item.getName());
            System.out.println("     " + item.getQuantity() + "\tx\t" + item.getPrice() + "\t= " + item.getTotal() + " Euro");
            total += item.getTotal();
        }

        System.out.println("\nTotal for shopping cart:\n     " + total + " Euro");
    }

    public static class ShoppingCartItem {
        private String name;
        private int quantity;
        private float price;

        public ShoppingCartItem(String name, int quantity, float price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public float getPrice() {
            return price;
        }

        public float getTotal() {
            return quantity * price;
        }
    }
}
