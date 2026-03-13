package pattern2_structural.struct1_composite.code.example1_product_boxes;

import java.util.Objects;

/**
 * Товар — конечный элемент иерархии (роль <b>Leaf</b> в паттерне Composite).
 *
 * <p>Представляет собой отдельный товар с фиксированной ценой.
 * Не может содержать дочерних элементов. Метод {@link #calcCost()}
 * просто возвращает собственную стоимость товара.
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * Item phone = new Item(999);
 * System.out.println(phone.calcCost()); // 999
 * }</pre>
 *
 * @see Leaf
 * @see Node
 * @see Box
 */
public class Item implements Leaf {
    /**
     * Родительский узел (коробка, в которой лежит товар).
     */
    private Node parent;

    /**
     * Цена товара.
     */
    private Integer cost;

    /**
     * Создаёт товар с указанным родителем и ценой.
     *
     * @param parent родительский элемент (коробка)
     * @param cost   цена товара
     */
    public Item(Node parent, Integer cost) {
        this.parent = parent;
        this.cost = cost;
    }

    /**
     * Создаёт товар с указанной ценой без привязки к родителю.
     *
     * @param cost цена товара
     */
    public Item(Integer cost) {
        this.cost = cost;
    }

    /**
     * Возвращает стоимость товара.
     *
     * @return цена товара
     */
    @Override
    public Integer calcCost() {
        return cost;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(cost, item.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }

    /**
     * Возвращает строковое представление товара.
     *
     * <p><b>Важно:</b> не включает ссылку на {@code parent}, чтобы избежать
     * бесконечной рекурсии при вызове {@code toString()} по цепочке родителей.
     *
     * @return строковое представление с ценой товара
     */
    @Override
    public String toString() {
        return "Item{cost=" + cost + '}';
    }
}
