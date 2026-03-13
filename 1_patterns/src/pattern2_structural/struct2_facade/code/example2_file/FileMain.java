package pattern2_structural.struct2_facade.code.example2_file;

import java.io.IOException;

/**
 * Демонстрация паттерна Фасад на примере работы с файловой системой.
 * <p>
 * Клиентский код использует {@link FileFacade} для выполнения операций
 * создания, записи, чтения и удаления файла, не взаимодействуя напрямую
 * с низкоуровневыми API {@link java.io.File} и {@link java.nio.file.Files}.
 */
public class FileMain {
    public static void main(String[] args) {
        FileFacade fileFacade = new FileFacadeImpl();
        String filePath = "example.txt";

        try {
            // Создание файла
            fileFacade.createFile(filePath);

            // Запись в файл
            fileFacade.writeFile(filePath, "Hello, Facade Pattern!");

            // Чтение из файла
            String content = fileFacade.readFile(filePath);
            System.out.println("File Content: " + content);

            // Удаление файла
            fileFacade.deleteFile(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
