package pattern2_structural.struct2_facade.code.example2_file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Реализация фасада для работы с файловой системой.
 * <p>
 * Инкапсулирует низкоуровневые операции с файлами ({@link java.io.File},
 * {@link java.nio.file.Files}, {@link java.io.BufferedWriter}), предоставляя
 * клиенту единый упрощённый интерфейс {@link FileFacade}.
 */
public class FileFacadeImpl implements FileFacade {

    /**
     * {@inheritDoc}
     * <p>
     * Если файл уже существует, выводит соответствующее сообщение.
     */
    @Override
    public void createFile(String path) throws IOException {
        File file = new File(path);
        if (file.createNewFile()) {
            System.out.println("File created: " + path);
        } else {
            System.out.println("File already exists: " + path);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Использует {@link Files#deleteIfExists} — не бросает исключение, если файл отсутствует.
     */
    @Override
    public void deleteFile(String path) throws IOException {
        Files.deleteIfExists(Paths.get(path));
        System.out.println("File deleted: " + path);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Использует try-with-resources для гарантированного закрытия потока записи.
     */
    @Override
    public void writeFile(String path, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        }
        System.out.println("Written to file: " + path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String readFile(String path) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(path)));
        System.out.println("Read from file: " + path);
        return content;
    }
}
