public class MainArguments {
    public static void main (String[] args) {
        System.out.println("Number of arguments: " + args.length);

        if (args.length > 0) {
            System.out.println("First arguments: " + args[0]);
        }

        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument " + (i + 1) + ": " + args[i]);
        }
    }
}
