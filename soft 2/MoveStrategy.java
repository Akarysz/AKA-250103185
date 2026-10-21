public interface MoveStrategy {
    void move();
    
    default String getSpeed() {
        return "";
    }
}
