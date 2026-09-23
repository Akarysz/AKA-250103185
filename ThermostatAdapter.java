/**
 * GoF Object Adapter: adapts LegacyThermostat to the SmartDevice interface.
 *
 * Safely handles null, empty, and unknown dial states without throwing exceptions.
 */
public class ThermostatAdapter implements SmartDevice {

    private final LegacyThermostat thermostat;

    public ThermostatAdapter(LegacyThermostat thermostat) {
        if (thermostat == null) {
            throw new IllegalArgumentException("LegacyThermostat must not be null");
        }
        this.thermostat = thermostat;
    }

    @Override
    public void turnOn() {
        String state = thermostat.checkDial();

        if ("IDLE".equals(state)) {
            thermostat.rotateDial("LOW");
        }
        // LOW, MEDIUM, MAX -> leave unchanged
        // null or unknown   -> do nothing (no exception)
    }

    @Override
    public void turnOff() {
        thermostat.rotateDial("IDLE");
    }

    @Override
    public boolean isOn() {
        String state = thermostat.checkDial();

        if (state == null) {
            return false;
        }

        switch (state) {
            case "LOW":
            case "MEDIUM":
            case "MAX":
                return true;
            default:            // "IDLE", "STUCK", "", or any unknown
                return false;
        }
    }

    @Override
    public int getPowerPercent() {
        String state = thermostat.checkDial();

        if (state == null) {
            return -1;
        }

        switch (state) {
            case "IDLE":   return 0;
            case "LOW":    return 33;
            case "MEDIUM": return 66;
            case "MAX":    return 100;
            default:       return -1;   // unknown / corrupted
        }
    }
}
