package be.webtechie.pinninginfo.util;

import javafx.scene.paint.Color;

public class Converter {

    private Converter() {
        // NOP
    }

    public static Color intToColor(int value) {
        return Color.rgb(
                (value & 0xFF0000) >> 16,
                (value & 0x00FF00) >> 8,
                (value & 0x0000FF));
    }
}
