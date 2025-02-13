package oop.intro10_input.code;

import java.util.Scanner;

public class ScannerTryResourcesMain {
    public static void main(String[] args) {
        // Используем try-with-resources для автоматического закрытия Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            // Ввод строки
            System.out.print("Введите ваше имя: ");
            String name = scanner.nextLine();

            // Ввод целого числа
            System.out.print("Введите ваш возраст: ");
            Integer age = scanner.nextInt();

            // Ввод числа с плавающей точкой
            System.out.print("Введите ваш рост (в метрах): ");
            Double height = scanner.nextDouble();

            // Вывод данных
            System.out.println("Ваше имя: " + name);
            System.out.println("Ваш возраст: " + age);
            System.out.println("Ваш рост: " + height + " м");
        } // Scanner будет автоматически закрыт здесь
    }
}
