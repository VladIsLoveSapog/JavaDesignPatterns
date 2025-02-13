package oop.intro12_loops.code;

public class WhileMain {
    public static void main(String[] args) {
        int i = 0;

        // Цикл продолжается, пока i меньше 5
        while (i < 5) {
            System.out.println("Итерация: " + i);
            i++; // Инкремент переменной, чтобы выйти из цикла
        }
    }
}
