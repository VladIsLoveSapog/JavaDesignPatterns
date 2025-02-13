package oop.intro12_loops.code;

import java.util.Scanner;

public class DoWhileMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        // Цикл do-while для проверки ввода на содержание только цифр
        do {
            System.out.print("Введите строку, содержащую только цифры: ");
            input = scanner.nextLine();
        } while (!isNumeric(input)); // Продолжаем цикл, пока строка не состоит только из цифр

        System.out.println("Спасибо! Вы ввели корректную строку: " + input);
    }

    // Метод для проверки, состоит ли строка только из цифр
    public static boolean isNumeric(String str) {
        // Если строка пуста или null, она не является числом
        if (str == null || str.isEmpty()) return false;

        // Проверяем каждый символ строки
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false; // Если хотя бы один символ не цифра, возвращаем false
        }
        return true; // Если все символы цифры, возвращаем true
    }
}
