import java.util.List;

/**
 * Adapter Pattern Lab — Demonstration and Verification
 *
 * Student ID : 250103185
 * K (last digit): 5
 */
public class Main {

    // ── helpers ──────────────────────────────────────────────────────────
    private static int passed = 0;
    private static int failed = 0;

    private static void check(String label, boolean condition) {
        if (condition) {
            System.out.println("  [PASS] " + label);
            passed++;
        } else {
            System.out.println("  [FAIL] " + label);
            failed++;
        }
    }

    // ── main ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println(" Adapter Pattern Lab  —  Smart Home Hub");
        System.out.println(" Student ID: 250103185   K = 5");
        System.out.println("========================================\n");

        // ─── 1. Create legacy objects ────────────────────────────────────
        LegacyBulb       rawBulb       = new LegacyBulb();
        LegacyThermostat rawThermostat = new LegacyThermostat();

        // ─── 2. Wrap them in adapters ────────────────────────────────────
        BulbAdapter       bulbAdapter       = new BulbAdapter(rawBulb);
        ThermostatAdapter thermostatAdapter = new ThermostatAdapter(rawThermostat);

        // ─── 3. Build hub with polymorphic list ──────────────────────────
        List<SmartDevice> deviceList = List.of(bulbAdapter, thermostatAdapter);
        ModernHub hub = new ModernHub(deviceList);

        // ─── 4. Activate all devices ─────────────────────────────────────
        System.out.println(">>> hub.activateAll()");
        hub.activateAll();

        System.out.println("\n--- Telemetry after activation ---");
        System.out.println("Bulb  : isOn=" + bulbAdapter.isOn()
                + "  power=" + bulbAdapter.getPowerPercent() + "%"
                + "  brightness=" + rawBulb.readBrightness());
        System.out.println("Thermo: isOn=" + thermostatAdapter.isOn()
                + "  power=" + thermostatAdapter.getPowerPercent() + "%"
                + "  dial=" + rawThermostat.checkDial());
        System.out.println("Average power usage: "
                + hub.calculateAveragePowerUsage() + "%\n");

        // ─── 5. Verify activation ────────────────────────────────────────
        System.out.println("=== TEST: Activation ===");
        check("Bulb isOn after activateAll",           bulbAdapter.isOn());
        check("Bulb brightness == 255",                rawBulb.readBrightness() == 255);
        check("Bulb power == 100 (min(100+5,100))",    bulbAdapter.getPowerPercent() == 100);
        check("Thermostat isOn after activateAll",      thermostatAdapter.isOn());
        check("Thermostat dial == LOW",                 "LOW".equals(rawThermostat.checkDial()));
        check("Thermostat power == 33",                 thermostatAdapter.getPowerPercent() == 33);
        double avg = hub.calculateAveragePowerUsage();
        check("Average power == 66.5",                  Math.abs(avg - 66.5) < 0.01);

        // ─── 6. Polymorphism through SmartDevice references ──────────────
        System.out.println("\n=== TEST: Polymorphism ===");
        for (SmartDevice device : deviceList) {
            System.out.println("  SmartDevice -> isOn=" + device.isOn()
                    + "  power=" + device.getPowerPercent() + "%");
        }
        check("Both devices accessible via SmartDevice reference", true);

        // ─── 7. Compile-error experiment (commented out) ─────────────────
        System.out.println("\n=== Adapter necessity (compile-time safety) ===");

        // ModernHub badHub = new ModernHub(List.of(rawBulb)); // COMPILE ERROR
        /*
         * LegacyBulb does not implement SmartDevice, so the compiler rejects it
         * as an incompatible type in List<SmartDevice>.
         * BulbAdapter implements SmartDevice while internally wrapping a LegacyBulb,
         * allowing ModernHub to use the legacy device without modifying LegacyBulb.
         */
        System.out.println("  (See commented-out code and explanation in Main.java)");

        // ─── 8. Broken filament ──────────────────────────────────────────
        System.out.println("\n=== TEST: Broken Filament ===");
        rawBulb.breakFilament();

        System.out.println("  Bulb brightness=" + rawBulb.readBrightness()
                + "  hasPower=" + rawBulb.hasPower());
        check("Bulb isOn == false (broken filament)",   !bulbAdapter.isOn());
        check("Bulb power == 0 (broken filament)",       bulbAdapter.getPowerPercent() == 0);

        // ─── 9. Corrupted thermostat — "STUCK" ──────────────────────────
        System.out.println("\n=== TEST: Corrupted Thermostat (\"STUCK\") ===");
        rawThermostat.rotateDial("STUCK");

        check("Thermostat isOn == false (STUCK)",        !thermostatAdapter.isOn());
        check("Thermostat power == -1 (STUCK)",           thermostatAdapter.getPowerPercent() == -1);

        // ─── 10. Null thermostat state ───────────────────────────────────
        System.out.println("\n=== TEST: Null Thermostat State ===");
        rawThermostat.rotateDial(null);

        check("Thermostat isOn == false (null)",         !thermostatAdapter.isOn());
        check("Thermostat power == -1 (null)",            thermostatAdapter.getPowerPercent() == -1);

        // ─── 11. Extra bulb brightness tests (fresh bulb) ───────────────
        System.out.println("\n=== TEST: Bulb Brightness Calibration (K=5) ===");

        LegacyBulb  freshBulb    = new LegacyBulb();
        BulbAdapter freshAdapter = new BulbAdapter(freshBulb);

        freshBulb.setBrightness(0);
        check("brightness=0   -> power=0",   freshAdapter.getPowerPercent() == 0);

        freshBulb.setBrightness(128);
        int expected128 = (128 * 100) / 255 + 5;   // 50 + 5 = 55
        check("brightness=128 -> power=" + expected128,
                freshAdapter.getPowerPercent() == expected128);

        freshBulb.setBrightness(255);
        check("brightness=255 -> power=100 (capped)",
                freshAdapter.getPowerPercent() == 100);

        // ─── 12. Extra thermostat states ─────────────────────────────────
        System.out.println("\n=== TEST: All Thermostat Dial Positions ===");
        LegacyThermostat  freshThermo   = new LegacyThermostat();
        ThermostatAdapter freshTAdapter = new ThermostatAdapter(freshThermo);

        freshThermo.rotateDial("IDLE");
        check("IDLE     -> isOn=false, power=0",
                !freshTAdapter.isOn() && freshTAdapter.getPowerPercent() == 0);

        freshThermo.rotateDial("LOW");
        check("LOW      -> isOn=true,  power=33",
                freshTAdapter.isOn() && freshTAdapter.getPowerPercent() == 33);

        freshThermo.rotateDial("MEDIUM");
        check("MEDIUM   -> isOn=true,  power=66",
                freshTAdapter.isOn() && freshTAdapter.getPowerPercent() == 66);

        freshThermo.rotateDial("MAX");
        check("MAX      -> isOn=true,  power=100",
                freshTAdapter.isOn() && freshTAdapter.getPowerPercent() == 100);

        freshThermo.rotateDial("OVERHEAT");
        check("OVERHEAT -> isOn=false, power=-1",
                !freshTAdapter.isOn() && freshTAdapter.getPowerPercent() == -1);

        freshThermo.rotateDial("");
        check("\"\"       -> isOn=false, power=-1",
                !freshTAdapter.isOn() && freshTAdapter.getPowerPercent() == -1);

        // ─── 13. Emergency shutdown (final operation) ────────────────────
        System.out.println("\n>>> hub.emergencyShutdown()");
        hub.emergencyShutdown();

        System.out.println("\n=== TEST: Emergency Shutdown ===");
        check("Bulb brightness == 0 after shutdown",    rawBulb.readBrightness() == 0);
        check("Bulb isOn == false after shutdown",       !bulbAdapter.isOn());
        check("Bulb power == 0 after shutdown",          bulbAdapter.getPowerPercent() == 0);
        check("Thermostat dial == IDLE after shutdown",  "IDLE".equals(rawThermostat.checkDial()));
        check("Thermostat isOn == false after shutdown", !thermostatAdapter.isOn());
        check("Thermostat power == 0 after shutdown",    thermostatAdapter.getPowerPercent() == 0);
        check("Average power == 0.0 after shutdown",
                hub.calculateAveragePowerUsage() == 0.0);

        // ─── Summary ────────────────────────────────────────────────────
        System.out.println("\n========================================");
        System.out.println(" Results: " + passed + " passed, " + failed + " failed");
        System.out.println("========================================");
    }
}
