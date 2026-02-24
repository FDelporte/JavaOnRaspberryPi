package be.webtechie.javafxspringledcontroller.led;

import javafx.scene.paint.Color;

/**
 * LedCommand as it is exchanged with the Arduino.
 */
public class LedCommand {
    private LedEffect ledEffect;
    private int speed;
    private Color color1;
    private Color color2;

    /**
     * Initialize a {@link LedCommand} with speed and colors.
     *
     * @param ledEffect {@link LedEffect}
     * @param speed The speed value
     * @param color1 {@link Color}
     * @param color2 {@link Color}
     */
    public LedCommand(LedEffect ledEffect, int speed, Color color1, Color color2) {
        this.ledEffect = ledEffect;
        this.speed = speed;
        this.color1 = color1;
        this.color2 = color2;
    }

    /**
     * Initialize a {@link LedCommand} from a ":"-separated String as exchanged via Mosquitto.
     *
     * @param command {@link String}
     */
    public LedCommand(String command) {
        String[] parts = command.split(":");

        this.ledEffect = parts.length >= 1 ? LedEffect.fromId(parts[0]) : LedEffect.UNDEFINED;
        this.speed = parts.length >= 2 ? Integer.parseInt(parts[1]) : 0;
        this.color1 = parts.length >= 5 ?
                Color.rgb(
                        Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])
                ): Color.BLACK;
        this.color2 = (parts.length >= 8) ?
                Color.rgb(
                        Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), Integer.parseInt(parts[7])
                ) : Color.BLACK;
    }

    /**
     * Convert to a ":"-separated String to be exchanged via Mosquitto.
     *
     * @return The command as ":"-separated String
     */
    public String toCommandString() {
        return this.ledEffect.getId() + ":"
                + speed + ":"
                + Math.round(color1.getRed() * 255) + ":"
                + Math.round(color1.getGreen() * 255) + ":"
                + Math.round(color1.getBlue() * 255) + ":"
                + Math.round(color2.getRed() * 255) + ":"
                + Math.round(color2.getGreen() * 255) + ":"
                + Math.round(color2.getBlue() * 255);
    }

    public LedEffect getLedEffect() {
        return ledEffect;
    }

    public int getSpeed() {
        return speed;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }
}
