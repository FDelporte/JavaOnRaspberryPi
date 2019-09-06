package be.webtechie.lednumberdisplaycontroller;

/**
 * Enum with definition of the segments to be highlighted.
 *
 * AAAA
 * F  B
 * GGGG
 * E  C
 * DDDD
 */
public enum HighlightTypes {
    A (true, true, true, false, true, true, true),
    B (true, true, true, true, true, true, true),
    C (true, false, false, true, true, true, false),
    D (true, true, true, true, true, true, false),
    E (true, false, false, true, true, true,true),
    // TODO complete alphabet
    ONE (false, true, true, false, false, false, false),
    TWO (true, true, false, true, true, false, true),
    THREE (true, true, true, true, false, false, true),
    FOUR (false, true, true, false, false, true, true),
    FIVE (true, false, true, true, false, true, true),
    SIX (true, false, true, true, true, true, true),
    SEVEN (true, true, true, false, false, false, false),
    EIGHT (true, true, true, true, true, true, true),
    NINE (true, true, true, true, false, true, true),

    CLEAR(false, false, false, false, false, false, false);

    private final boolean a;
    private final boolean b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final boolean g;

    HighlightTypes(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }

    public boolean isA() {
        return this.a;
    }

    public boolean isB() {
        return this.b;
    }

    public boolean isC() {
        return this.c;
    }

    public boolean isD() {
        return this.d;
    }

    public boolean isE() {
        return this.e;
    }

    public boolean isF() {
        return this.f;
    }

    public boolean isG() {
        return this.g;
    }
}
