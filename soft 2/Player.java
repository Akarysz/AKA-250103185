public class Player {
    private String name;
    private MoveStrategy moveStrategy;

    public Player() {
        this("Racer", new Walking());
    }

    public Player(MoveStrategy moveStrategy) {
        this("Racer", moveStrategy);
    }

    public Player(String name, MoveStrategy moveStrategy) {
        this.name = name;
        this.moveStrategy = moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void setStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public MoveStrategy getMoveStrategy() {
        return this.moveStrategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void move() {
        this.moveStrategy.move();
    }
}
