package pattern2_structural.struct1_composite.code.example1_product_boxes;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Коробка — реализация контейнера (роль <b>Composite</b> в паттерне Composite).
 *
 * <p>Коробка сама по себе не имеет цены. Её стоимость складывается
 * из стоимости всех вложенных элементов: товаров ({@link Item})
 * и других коробок.
 *
 * <p><b>Особенности реализации:</b>
 * <ul>
 *   <li>Хранит список дочерних элементов в {@link LinkedList};</li>
 *   <li>Метод {@link #calcCost()} рекурсивно обходит всё поддерево;</li>
 *   <li>Поддерживает ссылку на родителя для навигации вверх по дереву.</li>
 * </ul>
 *
 * @see Container
 * @see Node
 * @see Item
 */
public class Box implements Container {
    /**
     * Родительский узел в дереве. {@code null} для корневого элемента.
     */
    private Node parent;

    /**
     * Список дочерних элементов (товары и/или вложенные коробки).
     */
    private List<Node> children;

    /**
     * Создаёт пустую коробку с указанным родителем.
     *
     * @param parent родительский узел или {@code null} для корневой коробки
     */
    public Box(Node parent) {
        this.parent = parent;
        children = new LinkedList<>();
    }

    /**
     * Добавляет элемент в коробку.
     *
     * @param node элемент, который будет добавлен (товар или вложенная коробка)
     */
    @Override
    public void add(Node node) {
        children.add(node);
    }

    /**
     * Удаляет элемент из коробки.
     *
     * @param node элемент для удаления
     */
    @Override
    public void remove(Node node) {
        children.remove(node);
    }

    /**
     * Возвращает список дочерних элементов коробки.
     *
     * @return список дочерних узлов
     */
    @Override
    public List<Node> getChildren() {
        return children;
    }

    /**
     * Подсчитывает стоимость всех товаров в коробке.
     *
     * <p>Если внутри коробки находится другая коробка, происходит
     * рекурсивный обсчёт вложенной коробки и так далее.
     *
     * @return суммарная стоимость всех вложенных элементов
     */
    @Override
    public Integer calcCost() {
        int res = 0;
        for (var item : children) res += item.calcCost();
        return res;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(children, box.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }

    /**
     * Возвращает строковое представление коробки.
     *
     * <p><b>Важно:</b> не включает ссылку на {@code parent}, чтобы избежать
     * бесконечной рекурсии при вызове {@code toString()} по цепочке родителей.
     *
     * @return строковое представление с перечислением дочерних элементов
     */
    @Override
    public String toString() {
        return "Box{children=" + children + '}';
    }
}
