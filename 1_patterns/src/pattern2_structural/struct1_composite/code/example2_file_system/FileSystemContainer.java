package pattern2_structural.struct1_composite.code.example2_file_system;

import java.util.List;

/**
 * Интерфейс контейнера файловой системы (роль <b>Composite</b> в паттерне Composite).
 *
 * <p>Расширяет {@link FileSystemComponent}, добавляя операции управления
 * дочерними элементами:
 * <ul>
 *   <li>{@link #add(FileSystemComponent)} — добавление файла или поддиректории;</li>
 *   <li>{@link #remove(FileSystemComponent)} — удаление элемента;</li>
 *   <li>{@link #getChildren()} — получение списка всех дочерних элементов.</li>
 * </ul>
 *
 * <p>Контейнер может содержать как файлы ({@link File}), так и другие
 * директории ({@link Directory}), образуя рекурсивную древовидную структуру,
 * аналогичную реальной файловой системе.
 *
 * @see FileSystemComponent
 * @see Directory
 */
public interface FileSystemContainer extends FileSystemComponent {
    /**
     * Добавляет элемент в контейнер.
     *
     * @param component файл или поддиректория для добавления
     */
    void add(FileSystemComponent component);

    /**
     * Удаляет элемент из контейнера.
     *
     * @param component файл или поддиректория для удаления
     */
    void remove(FileSystemComponent component);

    /**
     * Возвращает список дочерних элементов контейнера.
     *
     * @return список дочерних элементов
     */
    List<FileSystemComponent> getChildren();
}
