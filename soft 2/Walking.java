public class Walking implements MoveStrategy {
    private final String speed = "5 km/h";

    @Override
    public void move() {
        System.out.println("Walking at " + speed);
    }

    @Override
    public String getSpeed() {
        return speed;
    }
}
