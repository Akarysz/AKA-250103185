public class ModernDigitalGame implements DigitalGame {
    private String title;

    public ModernDigitalGame() {
        this("Cyberpunk 2077");
    }

    public ModernDigitalGame(String title) {
        this.title = title;
    }

    @Override
    public void playDigital() {
        System.out.println("ModernDigitalGame: Streaming [" + title + "] directly from SSD storage at 4K 120FPS!");
    }

    public String getTitle() {
        return title;
    }
}
