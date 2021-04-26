package Lesson1;

public class Wall extends Obstacles{
    private final int height;

    public Wall(int height) {
        super("Стена");
        this.height = height;
    }

    @Override
    public boolean canOvercomeObstacle(Actions actions) {
        return actions.getJumpHeight() >= this.height;
    }

    @Override
    public int getSize() {
        return this.height;
    }

    //BEGIN task №2
    @Override
    public void overcomeObstacle(Actions action) {
        System.out.println(action.jump() + " через стену!");
    }
    //END task №2
}
