package be.webtechie.pinninginfo.data;

import javafx.scene.paint.Color;

/**
 * List of pin types in a header.
 */
public enum PinType {
    POWER("Power", Color.RED),
    GROUND("Ground", Color.BLACK),
    DIGITAL("Digital", Color.GREEN),
    DIGITAL_AND_PWM("DigitalAndPwm", Color.ORANGE),
    DIGITAL_NO_PULL_DOWN("DigitalNoPullDown",Color.PURPLE);

    private final String label;
    private final Color color;

    PinType(String label, Color color) {
        this.label = label;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public Color getColor() {
        return color;
    }
}
