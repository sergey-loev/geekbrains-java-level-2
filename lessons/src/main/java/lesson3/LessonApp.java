package lesson3;

import java.util.*;

public class LessonApp {
    /**1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
     *Найти и вывести список уникальных слов, из которых состоит массив
     *(дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
     *
     *2. Написать простой класс ТелефонныйСправочник, который хранит в себе список
     *фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add()
     * можно добавлять записи. С помощью метода get() искать номер телефона по фамилии.
     * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
     * тогда при запросе такой фамилии должны выводиться все телефоны.
     * */
    public static void main(String[] args) {
        //task1();
        task2();
    }

    private static void task1() {
        Map<String, Integer> hm = new HashMap<>();
        String[] words = {
                "Нога", "Слон", "Тост",
                "Ген", "Руль", "Нож",
                "Хлыст", "Горе", "Озеро",
                "Роман", "Руль", "Ген",
                "Пес", "Гора", "Машина","Пес",
                "Тост", "Руль", "Ген", "Пост"
        };

        for (String word : words) {
            if (hm.containsKey(word))
                hm.put(word, hm.get(word) + 1);
            else
                hm.put(word, 1);
        }
        System.out.println(hm);
    }

    private static void task2() {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иванов", "8999123321");
        phoneBook.add("Петров", "8912155326");
        phoneBook.add("Сидоров", "8917155552");
        phoneBook.add("Иванов", "8913455672");
        phoneBook.add("Петров", "899999999");
        phoneBook.add("Александров", "899111111");
        phoneBook.add("Буйнов", "89923231999");
        phoneBook.add("Иванов", "8888123113");
        phoneBook.add("Гладков", "8324325234");

        System.out.println("Иванов: " + phoneBook.get("Иванов"));
        System.out.println("Петров: " + phoneBook.get("Петров"));
        System.out.println("Сидоров: " + phoneBook.get("Сидоров"));
        System.out.println("Александров: " + phoneBook.get("Александров"));
        System.out.println("Буйнов: " + phoneBook.get("Буйнов"));
        System.out.println("Гладков: " + phoneBook.get("Гладков"));
    }
}








