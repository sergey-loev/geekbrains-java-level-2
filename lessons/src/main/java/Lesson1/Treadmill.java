package Lesson1;

public class Treadmill extends Obstacles{
    private final int distance;

    public Treadmill(int distance) {
        super("Беговая дорожка");
        this.distance = distance;
    }

    @Override
    public boolean canOvercomeObstacle(Actions actions) {
        return actions.getRunDistance() >= this.distance;
    }

    @Override
    public int getSize() {
        return this.distance;
    }

    //BEGIN task №2
    @Override
    public void overcomeObstacle(Actions action) {
        System.out.println(action.run()+" по беговой дорожке!");
    }
    //END task №2
}
