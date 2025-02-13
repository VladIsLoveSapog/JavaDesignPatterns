package oop.intro17_try_with_resources.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * А теперь можно так...
 * Так наверное лучше
 */
public class FileReadMain {
    public static void main(String[] args) {
        String filePath = "0_introduction/src/oop/intro17_try_with_resources/code/text.txt"; // Путь к файлу

        try(var reader = new BufferedReader(new FileReader(filePath))) {
            // Попытка чтения из файла сопровождается возможным IOException
            String content = reader.readLine();

            System.out.println("Содержимое файла:");
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
