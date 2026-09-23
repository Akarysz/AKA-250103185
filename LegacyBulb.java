public class LegacyBulb {

    private int brightnessLevel = 0;
    private boolean filamentConnected = true;

    public void setBrightness(int level) {
        if (level < 0) {
            brightnessLevel = 0;
        } else if (level > 255) {
            brightnessLevel = 255;
        } else {
            brightnessLevel = level;
        }
    }

    public int readBrightness() {
        return brightnessLevel;
    }

    public void breakFilament() {
        filamentConnected = false;
    }

    public boolean hasPower() {
        return filamentConnected && brightnessLevel > 0;
    }
}
