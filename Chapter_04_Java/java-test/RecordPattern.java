public class RecordPattern {
    public static void main(String[] args) {
        var p = new Point(1, 2);

        switch (p) {
            case Point(var x, var y) -> System.out.println("Point at " + x + ", " + y);
            case null -> System.out.println("No shape");
        }
    }

    public record Point(int x, int y) {}
}