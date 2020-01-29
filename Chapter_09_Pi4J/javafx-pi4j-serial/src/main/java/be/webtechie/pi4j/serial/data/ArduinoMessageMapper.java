package be.webtechie.pi4j.serial.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class ArduinoMessageMapper {
    public static ArduinoMessage map(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            return mapper.readValue(jsonString, ArduinoMessage.class);
        } catch (
                IOException ex) {
            System.err.println("Unable to parse the given string to Forecast object: "
                    + ex.getMessage());
            return null;
        }
    }
}
