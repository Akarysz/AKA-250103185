public class Jetpack implements MoveStrategy {
    private final String speed = "60 km/h";

    @Override
    public void move() {
        System.out.println("Jetpack at " + speed);
    }

    @Override
    public String getSpeed() {
        return speed;
    }
}
