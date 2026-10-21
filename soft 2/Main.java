
public class Main {

    public static void main(String[] args) {
        System.out.println("=== City Racer: Strategy Pattern Demo ===");

        Player player = new Player("CityRacer", new Walking());

        System.out.println("\n[Stage 1: Race Begins]");
        player.move();

        System.out.println("\n[Stage 2: Mid-Run Power-Up! Equipping Jetpack]");
        player.setMoveStrategy(new Jetpack());
        player.move();

        System.out.println("\n[Stage 3: Downhill Alleyway! Switching to Skateboard]");
        player.setMoveStrategy(new Skateboarding());
        player.move();

        System.out.println("\n[Stage 4: Automated Lap Sequence (Zero if/else)]");
        MoveStrategy[] trackSections = {
            new Walking(),
            new Skateboarding(),
            new Jetpack()
        };

        for (MoveStrategy strategy : trackSections) {
            player.setMoveStrategy(strategy);
            player.move();
        }
    }
}
