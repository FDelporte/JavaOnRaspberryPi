public class FlexibelConstructorBodies {
    public record SensorDevice(int pin, String type) {
        public SensorDevice {
            // Validation can now happen before super()
            if (pin < 1 || pin > 40) {
                throw new IllegalArgumentException("Invalid GPIO pin: " + pin);
            }
            if (!isValidSensorType(type)) {
                throw new IllegalArgumentException("Unsupported sensor type: " + type);
            }
        }

        private static boolean isValidSensorType(String type) {
            return type != null && (type.equals("thermometer") || type.equals("lightsensor"));
        }
    }

    void main() {
        var sensor1 = new SensorDevice(1, "thermometer");
        // var sensor2 = new SensorDevice(99, "lightsensor"); // This throws an exception
    }
}