package pattern2_structural.struct7_flyweight.code.example3_forest;

import java.util.Random;

/**
 * Точка входа — демонстрация паттерна Легковес на классическом примере леса из книги GoF.
 *
 * <p>Высаживает деревья трёх пород в случайных координатах и выводит статистику,
 * показывающую экономию памяти: при большом количестве деревьев
 * создаётся всего несколько объектов {@link TreeType}.
 */
public class ForestMain {
    public static void main(String[] args) {
        Forest forest = new Forest();
        Random random = new Random(42);

        String[][] treeData = {
                {"Берёза", "Зелёный", "Гладкая"},
                {"Ель", "Тёмно-зелёный", "Шершавая"},
                {"Дуб", "Зелёный", "Грубая"}
        };

        // Высаживаем 30 деревьев трёх пород в случайных координатах
        for (int i = 0; i < 30; i++) {
            String[] data = treeData[random.nextInt(treeData.length)];
            forest.plantTree(
                    random.nextInt(1000),
                    random.nextInt(1000),
                    data[0], data[1], data[2]
            );
        }

        System.out.println("=== Отрисовка леса ===");
        forest.draw();

        System.out.println("\n=== Статистика ===");
        System.out.println("Всего деревьев в лесу: " + forest.getTreeCount());
        System.out.println("Уникальных типов деревьев (Flyweight-объектов): " + TreeTypeFactory.getTypeCount());
        System.out.println("Экономия: " + (forest.getTreeCount() - TreeTypeFactory.getTypeCount())
                + " объектов не были созданы благодаря паттерну Легковес");
    }
}
