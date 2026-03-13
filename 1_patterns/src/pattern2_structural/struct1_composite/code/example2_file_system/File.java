package pattern2_structural.struct1_composite.code.example2_file_system;

/**
 * Файл — конечный элемент файловой системы (роль <b>Leaf</b> в паттерне Composite).
 *
 * <p>Представляет собой файл с именем и фиксированным размером.
 * Не может содержать дочерних элементов. Метод {@link #getSize()}
 * просто возвращает собственный размер файла.
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * File readme = new File("readme.txt", 1024);
 * System.out.println(readme.getSize()); // 1024
 * }</pre>
 *
 * @see FileSystemComponent
 * @see Directory
 */
public class File implements FileSystemComponent {
    /**
     * Имя файла.
     */
    private final String name;

    /**
     * Размер файла в байтах.
     */
    private final long size;

    /**
     * Создаёт файл с указанным именем и размером.
     *
     * @param name имя файла
     * @param size размер файла в байтах
     */
    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    /**
     * Возвращает размер файла.
     *
     * @return размер в байтах
     */
    @Override
    public long getSize() {
        return size;
    }

    /**
     * Возвращает имя файла.
     *
     * @return имя файла
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Выводит информацию о файле с заданным отступом.
     *
     * <p>Формат вывода: {@code indent + name + " (" + size + " bytes)"}
     *
     * @param indent строка отступа для текущего уровня вложенности
     */
    @Override
    public void showStructure(String indent) {
        System.out.println(indent + name + " (" + size + " bytes)");
    }

    @Override
    public String toString() {
        return "File{name='" + name + "', size=" + size + '}';
    }
}
