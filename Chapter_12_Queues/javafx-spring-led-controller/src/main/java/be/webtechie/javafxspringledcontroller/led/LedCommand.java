package be.webtechie.javafxspringledcontroller.led;

import javafx.scene.paint.Color;

class LedCommand {
    private LedEffect ledEffect;
    private int speed;
    private Color color1;
    private Color color2;

    LedCommand(LedEffect ledEffect, int speed, Color color1, Color color2) {
        this.ledEffect = ledEffect;
        this.speed = speed;
        this.color1 = color1;
        this.color2 = color2;
    }

    LedCommand(String command) {
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

    String toCommand() {
        return ledEffect.getId() + ":"
                + speed + ":"
                + Math.round(color1.getRed() * 255) + ":"
                + Math.round(color1.getGreen() * 255) + ":"
                + Math.round(color1.getBlue() * 255) + ":"
                + Math.round(color2.getRed() * 255) + ":"
                + Math.round(color2.getGreen() * 255) + ":"
                + Math.round(color2.getBlue() * 255);
    }

    LedEffect getLedEffect() {
        return ledEffect;
    }

    int getSpeed() {
        return speed;
    }

    Color getColor1() {
        return color1;
    }

    Color getColor2() {
        return color2;
    }
}
