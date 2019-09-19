import java.io.IOException;

public class HelloGpio {
    public static void main (String[] args) {
        System.out.println("Hello Gpio");

        try {
            Runtime.getRuntime().exec("gpio mode 3 out");

            var loopCounter = 0;
            var on = true;

            while (loopCounter < 10) {
                System.out.println("Changing LED to " + (on ? "on" : "off"));
                Runtime.getRuntime().exec("gpio write 3 " + (on ? "1" : "0"));

                on = !on;

                Thread.sleep(500);

                loopCounter++;
            }
        } catch (IOException ex) {
            System.err.println("IOException from Runtim: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException from Thread: " + ex.getMessage());
        }
    }
}
