package pattern2_structural.struct1_composite.code.example2_file_system;

import java.util.ArrayList;
import java.util.List;

/**
 * Директория — реализация контейнера файловой системы (роль <b>Composite</b> в паттерне Composite).
 *
 * <p>Директория может содержать как файлы ({@link File}), так и другие
 * директории, образуя рекурсивную древовидную структуру, аналогичную
 * реальной файловой системе.
 *
 * <p><b>Особенности реализации:</b>
 * <ul>
 *   <li>Хранит список дочерних элементов в {@link ArrayList};</li>
 *   <li>Метод {@link #getSize()} рекурсивно суммирует размеры всех вложенных элементов;</li>
 *   <li>Метод {@link #showStructure(String)} выводит содержимое в виде дерева.</li>
 * </ul>
 *
 * @see FileSystemContainer
 * @see FileSystemComponent
 * @see File
 */
public class Directory implements FileSystemContainer {
    /**
     * Имя директории.
     */
    private final String name;

    /**
     * Список дочерних элементов (файлы и поддиректории).
     */
    private final List<FileSystemComponent> children;

    /**
     * Создаёт пустую директорию с указанным именем.
     *
     * @param name имя директории
     */
    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    /**
     * Добавляет элемент в директорию.
     *
     * @param component файл или поддиректория для добавления
     */
    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
    }

    /**
     * Удаляет элемент из директории.
     *
     * @param component файл или поддиректория для удаления
     */
    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    /**
     * Возвращает список дочерних элементов директории.
     *
     * @return список дочерних элементов
     */
    @Override
    public List<FileSystemComponent> getChildren() {
        return children;
    }

    /**
     * Рекурсивно вычисляет суммарный размер всех вложенных элементов.
     *
     * <p>Для каждого дочернего элемента вызывается {@link FileSystemComponent#getSize()}.
     * Если дочерний элемент — директория, вычисление происходит рекурсивно.
     *
     * @return суммарный размер содержимого в байтах
     */
    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }

    /**
     * Возвращает имя директории.
     *
     * @return имя директории
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Выводит содержимое директории в виде дерева с отступами.
     *
     * <p>Формат вывода:
     * <pre>
     * indent + name + "/" + " (total: " + size + " bytes)"
     *   indent + "  " + child1
     *   indent + "  " + child2
     * </pre>
     *
     * @param indent строка отступа для текущего уровня вложенности
     */
    @Override
    public void showStructure(String indent) {
        System.out.println(indent + name + "/ (total: " + getSize() + " bytes)");
        for (FileSystemComponent child : children) {
            child.showStructure(indent + "  ");
        }
    }

    @Override
    public String toString() {
        return "Directory{name='" + name + "', children=" + children + '}';
    }
}
