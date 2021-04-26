package Lesson1;

public class Human implements Actions{
    private final String name;
    private final int runDistance;
    private final int jumpHeight;

    public Human(String name,int runDistance,int jumpHeight) {
        this.name = name;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }

    //BEGIN task №1
    @Override
    public String jump() {
        return ("Человек " + this.name + " прыгнул" );
    }

    @Override
    public String run() {
     return ("Человек " + this.name + " пробежал");
    }
    //END task №1

    @Override
    public int getRunDistance() {
        return runDistance;
    }

    @Override
    public int getJumpHeight() {
        return jumpHeight;
    }

    @Override
    public String getName() {
        return "Человек " + this.name;
    }

}
