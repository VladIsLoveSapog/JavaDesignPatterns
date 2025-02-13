package oop.intro15_exceptions.code;

public class TryCatchMain {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // Здесь возникнет исключение
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка: выход за пределы массива");
            e.printStackTrace(); // Вывод стека вызовов для отладки
        }
    }
}
