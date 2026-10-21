public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   RETRO GAME PLAYER: ADAPTER PATTERN DEMO");
        System.out.println("==================================================\n");

        ModernConsole console = new ModernConsole();

        System.out.println("--- 1. Playing Native Modern Digital Game ---");
        DigitalGame modernGame = new ModernDigitalGame("Cyberpunk 2077");
        console.playGame(modernGame);
        System.out.println();

        System.out.println("--- 2. The Legacy Retro Cartridge ---");
        RetroCartridge retroGame = new RetroCartridge("Super Mario Bros (1985)");
        System.out.println("Notice: RetroCartridge only provides insertCartridge().");
        System.out.println("ModernConsole only accepts DigitalGame with playDigital().");
        System.out.println("// Compilation Error if we attempt: console.playGame(retroGame);");
        System.out.println();

        System.out.println("--- 3. Running Retro Game via CartridgeAdapter ---");
        DigitalGame adaptedGame = new CartridgeAdapter(retroGame);
        console.playGame(adaptedGame);
        System.out.println();


        System.out.println("--- 4. Running Another Adapted Retro Game ---");
        RetroCartridge zeldaCartridge = new RetroCartridge("The Legend of Zelda (1986)");
        DigitalGame adaptedZelda = new CartridgeAdapter(zeldaCartridge);
        console.loadGame(adaptedZelda);
        console.playDigital();
        System.out.println();


        System.out.println("--- 5. Polymorphic Game Library in ModernConsole ---");
        DigitalGame[] gameLibrary = {
            new ModernDigitalGame("Elden Ring"),
            new CartridgeAdapter(new RetroCartridge("Metroid (1986)")),
            new CartridgeAdapter(new RetroCartridge("Mega Man 2 (1988)")),
            new ModernDigitalGame("Starfield")
        };

        for (DigitalGame game : gameLibrary) {
            console.playGame(game);
            System.out.println("----------------------------------------");
        }

        System.out.println("\nAdapter Pattern successfully executed: Neither ModernConsole nor RetroCartridge was modified!");
    }
}
