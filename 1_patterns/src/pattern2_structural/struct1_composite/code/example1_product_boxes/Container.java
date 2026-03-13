package pattern2_structural.struct1_composite.code.example1_product_boxes;

import java.util.List;

/**
 * Интерфейс узла-контейнера (роль <b>Composite</b> в паттерне Composite).
 *
 * <p>Расширяет {@link Node}, добавляя операции управления дочерними элементами:
 * <ul>
 *   <li>{@link #add(Node)} — добавление элемента в контейнер;</li>
 *   <li>{@link #remove(Node)} — удаление элемента из контейнера;</li>
 *   <li>{@link #getChildren()} — получение списка всех дочерних элементов.</li>
 * </ul>
 *
 * <p>Контейнер может содержать как листья ({@link Leaf}), так и другие
 * контейнеры, образуя рекурсивную древовидную структуру.
 *
 * @see Node
 * @see Box
 */
public interface Container extends Node {
    /**
     * Добавляет элемент в контейнер.
     *
     * @param node элемент, который будет добавлен (лист или вложенный контейнер)
     */
    void add(Node node);

    /**
     * Удаляет элемент из контейнера.
     *
     * @param node элемент для удаления
     */
    void remove(Node node);

    /**
     * Возвращает список дочерних элементов контейнера.
     *
     * @return список дочерних узлов
     */
    List<Node> getChildren();
}
