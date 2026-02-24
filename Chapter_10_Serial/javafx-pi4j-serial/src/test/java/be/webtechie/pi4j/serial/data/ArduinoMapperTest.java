package be.webtechie.pi4j.serial.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ArduinoMapperTest {
    @Test
    public void testStringValue() {
        String json = "{\"type\":\"echo\",\"value\":\"Timestamp: 1580241952077\"}";
        ArduinoMessage arduinoMessage = ArduinoMessageMapper.map(json);
        assertEquals("echo", arduinoMessage.type);
        assertEquals("Timestamp: 1580241952077", arduinoMessage.value);
    }

    @Test
    public void testIntValue() {
        String json = "{\"type\":\"light\",\"value\":394}";
        ArduinoMessage arduinoMessage = ArduinoMessageMapper.map(json);
        assertEquals("light", arduinoMessage.type);
        assertEquals(395, arduinoMessage.getIntValue(), 1);
    }

    @Test
    public void testIntStringValue() {
        String json = "{\"type\":\"light\",\"value\":\"394\"}";
        ArduinoMessage arduinoMessage = ArduinoMessageMapper.map(json);
        assertEquals("light", arduinoMessage.type);
        assertEquals(395, arduinoMessage.getIntValue(), 1);
    }

    @Test
    public void testIntNullValue() {
        String json = "{\"type\":\"light\",\"value\":\"zrea\"}";
        ArduinoMessage arduinoMessage = ArduinoMessageMapper.map(json);
        assertEquals("light", arduinoMessage.type);
        assertNull(arduinoMessage.getIntValue());
    }

    @Test
    public void testFloatValue() {
        String json = "{\"type\":\"light\",\"value\":394.123}";
        ArduinoMessage arduinoMessage = ArduinoMessageMapper.map(json);
        assertEquals("light", arduinoMessage.type);
        assertEquals(394.123, arduinoMessage.getFloatValue(), 0.001);
    }

    @Test
    public void testFloatStringValue() {
        String json = "{\"type\":\"light\",\"value\":\"394.123\"}";
        ArduinoMessage arduinoMessage = ArduinoMessageMapper.map(json);
        assertEquals("light", arduinoMessage.type);
        assertEquals(394.123, arduinoMessage.getFloatValue(), 0.001);
    }

    @Test
    public void testFloatNullValue() {
        String json = "{\"type\":\"light\",\"value\":\"erar\"}";
        ArduinoMessage arduinoMessage = ArduinoMessageMapper.map(json);
        assertEquals("light", arduinoMessage.type);
        assertNull( arduinoMessage.getFloatValue());
    }
}
