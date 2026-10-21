public class RetroCartridge {
    private String title;

    public RetroCartridge() {
        this("Super Mario Bros (1985)");
    }

    public RetroCartridge(String title) {
        this.title = title;
    }

    public void insertCartridge() {
        System.out.println("RetroCartridge: Physical cartridge [" + title + "] inserted into slot.");
        System.out.println("RetroCartridge: Reading 8-bit ROM contacts... Booting retro game!");
    }

    public String getTitle() {
        return title;
    }

    public String getGameTitle() {
        return title;
    }
}
