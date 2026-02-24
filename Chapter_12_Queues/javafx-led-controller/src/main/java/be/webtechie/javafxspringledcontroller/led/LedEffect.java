package be.webtechie.javafxspringledcontroller.led;

/**
 * The list of available effects.
 * By using enums with variables, we can define here which UI-elements must be enabled when an effect is selected.
 */
public enum LedEffect {
    UNDEFINED("0", false, 0, 0, false, false),
    STATIC("1", false, 0, 0, true, false),
    STATIC_FADE("2", false, 0, 0, true, true),
    BLINKING("3", true, 25, 200, true, true),
    RUNNING("4", true, 25, 100, true, true),
    FADING_RAINBOW("5", true, 1, 25, false, false),
    STATIC_RAINBOW("6", false, 0, 0, false, false),
    ALL_WHITE("98", false, 0, 0, false, false),
    ALL_OUT("99", false, 0, 0, false, false);

    private String id;
    private boolean useSpeed;
    private int minimumSpeed;
    private int maximumSpeed;
    private boolean useColor1;
    private boolean useColor2;

    LedEffect(String id, boolean useSpeed, int minimumSpeed, int maximumSpeed, boolean useColor1, boolean useColor2) {
        this.id = id;
        this.useSpeed = useSpeed;
        this.minimumSpeed = minimumSpeed;
        this.maximumSpeed = maximumSpeed;
        this.useColor1 = useColor1;
        this.useColor2 = useColor2;
    }

    public String getId() {
        return id;
    }

    public boolean useSpeed() {
        return useSpeed;
    }

    public int getMinimumSpeed() {
        return minimumSpeed;
    }

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    public boolean useColor1() {
        return useColor1;
    }

    public boolean useColor2() {
        return useColor2;
    }

    public static LedEffect fromId(String id) {
        for (LedEffect ledEffect : LedEffect.values()) {
            if (ledEffect.id.equals(id)) {
                return ledEffect;
            }
        }

        return UNDEFINED;
    }
}
