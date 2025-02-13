package oop.intro16_file_operations.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Пример демонстрационный - а как оно было раньше....
 * Сейчас чуть лучше :)
 */
public class FileReadMain {
    public static void main(String[] args) {
        String filePath = "0_introduction/src/oop/intro16_file_operations/code/text.txt"; // Путь к файлу

        BufferedReader reader = null;
        try {
            // Попытка открытия файла на чтение сопровождается возможным IOException
            reader = new BufferedReader(new FileReader(filePath));

            // Попытка чтения из файла сопровождается возможным IOException
            String content = reader.readLine();

            System.out.println("Содержимое файла:");
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // В любом случае (с исключениями или без) необходимо закрыть поток чтения
            try {
                // Попытка закрытия потока чтения сопровождается возможным IOException
                reader.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
    }
}
