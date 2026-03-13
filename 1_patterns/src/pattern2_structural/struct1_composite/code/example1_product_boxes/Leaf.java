package pattern2_structural.struct1_composite.code.example1_product_boxes;

/**
 * Маркерный интерфейс конечного элемента иерархии (роль <b>Leaf</b> в паттерне Composite).
 *
 * <p>Лист — это узел дерева, у которого не может быть дочерних элементов.
 * В данном примере роль листа выполняет {@link Item} (товар).
 *
 * <p>Интерфейс не объявляет собственных методов, а лишь помечает класс
 * как конечный элемент, что позволяет типобезопасно различать листья
 * и контейнеры ({@link Container}).
 *
 * @see Node
 * @see Item
 */
public interface Leaf extends Node {
}
