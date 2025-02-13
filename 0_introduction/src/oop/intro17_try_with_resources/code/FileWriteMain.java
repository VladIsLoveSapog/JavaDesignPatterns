package oop.intro17_try_with_resources.code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * А теперь можно так...
 * Так наверное лучше
 */
public class FileWriteMain {
    public static void main(String[] args) {
        String filePath = "0_introduction/src/oop/intro17_try_with_resources/code/text.txt"; // Путь к файлу
        String content = "Возможно вы захотите читать и/или сохранять данные в файл при выполнении лаборатоной работы."; // Содержимое для записи

        try (var writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);

            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
