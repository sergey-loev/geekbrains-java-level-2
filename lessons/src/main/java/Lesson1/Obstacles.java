package Lesson1;

public abstract class Obstacles {

    private final String name;

    public Obstacles(String name) {
        this.name = name;
    }

    public abstract int getSize();

    //BEGIN task №2
    public abstract void overcomeObstacle(Actions actions);
    //END task №2

    public abstract boolean canOvercomeObstacle(Actions actions);

    public String getName() {
        return name;
    }
}
