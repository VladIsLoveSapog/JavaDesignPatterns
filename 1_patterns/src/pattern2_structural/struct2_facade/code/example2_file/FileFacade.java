package pattern2_structural.struct2_facade.code.example2_file;

import java.io.IOException;

/**
 * Интерфейс фасада для работы с файловой системой.
 * <p>
 * Определяет простой контракт для основных операций с файлами: создание, удаление,
 * запись и чтение. Скрывает от клиента детали работы с низкоуровневыми API
 * ({@link java.io.File}, {@link java.nio.file.Files}, {@link java.io.BufferedWriter}).
 *
 * @see FileFacadeImpl
 */
public interface FileFacade {

    /**
     * Создаёт новый файл по указанному пути.
     *
     * @param path путь к файлу
     * @throws IOException если произошла ошибка ввода-вывода
     */
    void createFile(String path) throws IOException;

    /**
     * Удаляет файл по указанному пути, если он существует.
     *
     * @param path путь к файлу
     * @throws IOException если произошла ошибка ввода-вывода
     */
    void deleteFile(String path) throws IOException;

    /**
     * Записывает текстовое содержимое в файл по указанному пути.
     *
     * @param path    путь к файлу
     * @param content содержимое для записи
     * @throws IOException если произошла ошибка ввода-вывода
     */
    void writeFile(String path, String content) throws IOException;

    /**
     * Читает и возвращает содержимое файла по указанному пути.
     *
     * @param path путь к файлу
     * @return содержимое файла в виде строки
     * @throws IOException если произошла ошибка ввода-вывода
     */
    String readFile(String path) throws IOException;
}
