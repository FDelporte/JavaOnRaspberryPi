public class FlexibelConstructorBodies {
    public record SensorDevice(int pin, String type) {
        // Validation can now happen before super()
        if (pin < 1 || pin > 40) {
            throw new IllegalArgumentException("Invalid GPIO pin: " + pin);
        }
        if (!isValidSensorType(type)) {
            throw new IllegalArgumentException("Unsupported sensor type: " + type);
        }

        super(pin, type); // Call to super() after validation
    }

    void main() {
        var sensor1 = new SensorDevice(1, "thermometer");
        var sensor2 = new SensorDevice(99, "lightsensor");
    }
}