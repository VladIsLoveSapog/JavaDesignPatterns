package pattern2_structural.struct1_composite.code.example1_product_boxes;

/**
 * Демонстрация паттерна Composite на примере иерархии товаров и коробок.
 *
 * <p>Пример показывает, как с помощью паттерна Компоновщик можно
 * единообразно обсчитать стоимость товаров, вложенных в коробки
 * на произвольную глубину.
 *
 * <p><b>Структура дерева в данном примере:</b>
 * <pre>
 *   root (Box)                      cost = 310
 *   ├── secondBox (Box)             cost = 300
 *   │   ├── Item(50)
 *   │   ├── Item(100)
 *   │   └── Item(150)
 *   ├── Item(3)
 *   └── Item(7)
 * </pre>
 *
 * <p>Самое главное, что это дерево, а как оно реализовано — это уже вторично.
 * Компоновщик — это и есть дерево.
 *
 * @see Node
 * @see Container
 * @see Box
 * @see Item
 */
public class Main {
    public static void main(String[] args) {
        // Создаём корневую коробку (без родителя)
        Container root = new Box(null);

        // Создаём вложенную коробку и добавляем её в корневую
        Container secondBox = new Box(root);
        root.add(secondBox);

        // Наполняем вложенную коробку товарами
        secondBox.add(new Item(50));
        secondBox.add(new Item(100));
        secondBox.add(new Item(150));

        // Добавляем товары напрямую в корневую коробку
        root.add(new Item(3));
        root.add(new Item(7));

        // Подсчёт стоимости вложенной коробки (50 + 100 + 150 = 300)
        System.out.println("Стоимость вложенной коробки: " + secondBox.calcCost());

        // Подсчёт стоимости всего заказа (300 + 3 + 7 = 310)
        System.out.println("Стоимость всего заказа: " + root.calcCost());
    }
}
