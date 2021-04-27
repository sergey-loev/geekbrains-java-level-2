package Lesson2;

public class MyArrayDataException extends Exception{
    public MyArrayDataException(int i, int j) {
        super(String.format("Обнаружена ошибка в элементе массива [%d, %d].", i + 1, j + 1));
    }
}
