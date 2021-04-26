package Lesson1;

public class LessonApp {
    /**1.Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
     * Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
     *
     * 2.Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники
     * должны выполнять соответствующие действия (бежать или прыгать), результат выполнения
     * печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
     *
     * 3.Создайте два массива: с участниками и препятствиями, и заставьте
     * всех участников пройти этот набор препятствий.
     *
     * 4.* У препятствий есть длина (для дорожки) или высота (для стены), а участников
     * ограничения на бег и прыжки. Если участник не смог пройти одно из препятствий,
     * то дальше по списку он препятствий не идет.*/

    public static void main(String[] args) {
        boolean result = true;

        Cat cat     = new Cat("Саймон", 150, 5);
        Human human = new Human("Иван", 500, 3);
        Robot robot = new Robot("Фёдор", 100, 1);

        //BEGIN task №3
        Actions[] actions = new Actions[]{cat,human,robot};
        Obstacles[] obstacles = new Obstacles[]{new Treadmill(80),
                                                new Wall(1),
                                                new Treadmill(130),
                                                new Treadmill(150),
                                                new Wall(4)};
        /**
        *for (Actions action : actions) {
        *    for (Obstacles obstacle : obstacles) {
        *        obstacle.overcomeObstacle(action);
        *    }
        *    System.out.println("\n");
        *}*/
        //END task №2

        //BEGIN task №4
        for (Actions action : actions) {
            for (Obstacles obstacle : obstacles) {
                result = obstacle.canOvercomeObstacle(action);
                if (result) {
                    System.out.println(action.getName() + " преодолел препятствие: " + obstacle.getName() + "(" + obstacle.getSize() + ")");
                } else {
                    System.out.println(action.getName() + " не смог преодолеть препятствие: " + obstacle.getName() + "(" + obstacle.getSize() + "). И выбыл!!!" + "\n");
                    break;
                }
            }
            if (result) {
                System.out.println("\n" + action.getName() + " преодолел все препятствия!!!!" + "\n");
            }
        }
        //END task №3
    }
}
