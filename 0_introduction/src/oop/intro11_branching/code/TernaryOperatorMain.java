package oop.intro11_branching.code;

public class TernaryOperatorMain {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        // Используем тернарный оператор для выбора наибольшего числа
        int max = (a > b) ? a : b;

        System.out.println("Наибольшее число: " + max);
    }
}
