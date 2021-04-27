package Lesson2;

public class LessonApp {
    /**1. Напишите метод, на вход которого подаётся двумерный
     * строковый массив размером 4х4, при подаче массива другого
     * размера необходимо бросить исключение MyArraySizeException.
     *
     * 2. Далее метод должен пройтись по всем элементам массива,
     * преобразовать в int, и просуммировать. Если в каком-то элементе
     * массива преобразование не удалось (например, в ячейке лежит
     * символ или текст вместо числа), должно быть брошено исключение
     * MyArrayDataException, с детализацией в какой именно ячейке
     * лежат неверные данные.
     *
     * 3. В методе main() вызвать полученный метод, обработать
     * возможные исключения MySizeArrayException
     * и MyArrayDataException, и вывести результат расчета.*/

    public static void main(String[] args) {

        String[][] stringArr1 = {{"1", "3", "2", "7"},
                                 {"3", "5", "8", "1"},
                                 {"6", "2", "5", "2"}};

        String[][] stringArr2 = {{"1", "3", "2", "7"},
                                 {"3", "5", "*", "1"},
                                 {"6", "2", "5", "2"},
                                 {"9", "4", "4", "1"}};

        String[][] stringArr3 = {{"1", "3", "2", "7"},
                                 {"3", "5", "1"},
                                 {"6", "2", "5", "2"},
                                 {"9", "4", "4", "1"}};

        String[][] stringArr4 = {{"1", "3", "2", "7"},
                                 {"3", "5", "8", "1"},
                                 {"6", "2", "5", "2"},
                                 {"9", "4", "4", "1"}};

        String[][][] arrOfArr = {stringArr1, stringArr2, stringArr3, stringArr4};

        for(String[][] strArr : arrOfArr){
            try {
                int sumOfStringArr = LessonApp.parseIntAndSum(strArr);
                System.out.printf("Сумма всех элементов массива = %d!!!%n", sumOfStringArr);
            } catch (MyArraySizeException | MyArrayDataException e) {
                e.printStackTrace();
            }
        }
    }

    public static int parseIntAndSum(String[][] stringArray) throws MyArraySizeException, MyArrayDataException {
        int sumCount = 0;

        if (stringArray.length != 4) throw new MyArraySizeException("Неверное количество строк в массиве.");
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].length != 4)
                throw new MyArraySizeException(String.format("Неверное количество столбцов в %d-й строке.", i + 1));
            for (int j = 0; j < stringArray[i].length; j++) {
                try {
                    sumCount += Integer.parseInt(stringArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sumCount;
    }
}

