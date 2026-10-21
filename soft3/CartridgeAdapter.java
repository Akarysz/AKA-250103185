public class CartridgeAdapter implements ModernGame {
    private RetroCartridge retroCartridge;

    public CartridgeAdapter() {
        this(new RetroCartridge());
    }

    public CartridgeAdapter(RetroCartridge retroCartridge) {
        this.retroCartridge = retroCartridge;
    }

    public CartridgeAdapter(String title) {
        this(new RetroCartridge(title));
    }

    @Override
    public void playDigital() {
        System.out.println("[CartridgeAdapter] Converting digital signal to 72-pin cartridge interface...");
        if (retroCartridge != null) {
            retroCartridge.insertCartridge();
            System.out.println("[CartridgeAdapter] Emulation layer active: 8-bit game running on modern display!");
        } else {
            System.out.println("[CartridgeAdapter] Error: No retro cartridge inserted into the adapter slot.");
        }
    }

    public void insertCartridge() {
        if (retroCartridge != null) {
            retroCartridge.insertCartridge();
        }
    }

    public RetroCartridge getRetroCartridge() {
        return retroCartridge;
    }

    public RetroCartridge getCartridge() {
        return retroCartridge;
    }

    public void setRetroCartridge(RetroCartridge retroCartridge) {
        this.retroCartridge = retroCartridge;
    }
}
