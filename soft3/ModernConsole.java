public class ModernConsole {
    private DigitalGame loadedGame;

    public ModernConsole() {}

    public ModernConsole(DigitalGame game) {
        this.loadedGame = game;
    }

    public void loadGame(DigitalGame game) {
        this.loadedGame = game;
        System.out.println("ModernConsole: Digital media loaded into system.");
    }

    public void playDigital() {
        if (loadedGame != null) {
            System.out.println("ModernConsole: Initiating digital game sequence...");
            loadedGame.playDigital();
        } else {
            System.out.println("ModernConsole: No game loaded. Please insert or load a game.");
        }
    }

    public void playDigital(DigitalGame game) {
        loadGame(game);
        playDigital();
    }

    public void playGame(DigitalGame game) {
        playDigital(game);
    }

    public void runGame(DigitalGame game) {
        playDigital(game);
    }

    public void play(DigitalGame game) {
        playDigital(game);
    }
}
