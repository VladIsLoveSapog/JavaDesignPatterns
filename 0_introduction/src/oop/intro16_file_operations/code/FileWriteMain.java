package oop.intro16_file_operations.code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Пример демонстрационный - а как оно было раньше....
 * Сейчас чуть лучше :)
 */
public class FileWriteMain {
    public static void main(String[] args) {
        String filePath = "0_introduction/src/oop/intro16_file_operations/code/text.txt"; // Путь к файлу
        String content = "Возможно вы захотите читать и/или сохранять данные в файл при выполнении лаборатоной работы."; // Содержимое для записи

        BufferedWriter writer = null;

        try {
            // Попытка открытия файла на запись сопровождается возможным IOException
            writer = new BufferedWriter(new FileWriter(filePath));

            // Попытка записи в файл сопровождается возможным IOException
            writer.write(content);

            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // В любом случае (с исключениями или без) необходимо закрыть поток записи
            try {
                // Попытка закрытия потока записи сопровождается возможным IOException
                writer.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
    }
}
