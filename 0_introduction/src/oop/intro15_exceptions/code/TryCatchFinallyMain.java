package oop.intro15_exceptions.code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TryCatchFinallyMain {
    public static void main(String[] args) {
        FileInputStream file = null;
        try {
            file = new FileInputStream("test.txt");
            // Чтение файла
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.out.println("Ошибка при закрытии файла");
                }
            }
        }
    }
}
