public class Skateboarding implements MoveStrategy {
    private final String speed = "15 km/h";

    @Override
    public void move() {
        System.out.println("Skateboarding at " + speed);
    }

    @Override
    public String getSpeed() {
        return speed;
    }
}
