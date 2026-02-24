package be.webtechie.pi4j.serial.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArduinoMessage {
    @JsonProperty("type")
    public String type;

    @JsonProperty("value")
    public String value;

    public Integer getIntValue() {
        if (this.value.matches("-?(0|[1-9]\\d*)")) {
            return Integer.parseInt(this.value);
        }

        return null;
    }

    public Float getFloatValue() {
        if (this.value.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            return Float.parseFloat(this.value);
        }

        return null;
    }
}
