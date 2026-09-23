/**
 * GoF Object Adapter: adapts LegacyBulb to the SmartDevice interface.
 *
 * Student ID: 250103185
 * K (last digit): 5
 */
public class BulbAdapter implements SmartDevice {

    private final LegacyBulb bulb;

    public BulbAdapter(LegacyBulb bulb) {
        if (bulb == null) {
            throw new IllegalArgumentException("LegacyBulb must not be null");
        }
        this.bulb = bulb;
    }

    @Override
    public void turnOn() {
        bulb.setBrightness(255);
    }

    @Override
    public void turnOff() {
        bulb.setBrightness(0);
    }

    @Override
    public boolean isOn() {
        return bulb.hasPower() && bulb.readBrightness() > 0;
    }

    @Override
    public int getPowerPercent() {
        int rawBrightness = bulb.readBrightness();

        if (rawBrightness == 0) {
            return 0;
        }

        if (!bulb.hasPower()) {
            return 0;
        }

        int rawPercent = (rawBrightness * 100) / 255;   // integer division floors
        int calibrated = rawPercent + 5;                 // K = 5

        return Math.min(calibrated, 100);
    }
}
