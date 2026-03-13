package pattern2_structural.struct7_flyweight.code.example3_forest;

import java.util.ArrayList;
import java.util.List;

/**
 * Клиент (Client) — лес, содержащий множество деревьев.
 *
 * <p>Хранит список всех деревьев ({@link Tree}) в поле {@code trees}.
 * При посадке дерева через {@link #plantTree(int, int, String, String, String)}
 * запрашивает тип дерева у фабрики {@link TreeTypeFactory}, которая возвращает
 * уже существующий легковес из кэша или создаёт новый.
 *
 * <p>Благодаря паттерну Легковес тысячи деревьев в лесу разделяют всего
 * несколько объектов {@link TreeType}, что значительно экономит память.
 */
public class Forest {
    private final List<Tree> trees = new ArrayList<>();

    /**
     * Высаживает дерево с указанными координатами и характеристиками.
     *
     * <p>Тип дерева ({@link TreeType}) запрашивается у фабрики, а не создаётся напрямую.
     *
     * @param x       координата по горизонтали
     * @param y       координата по вертикали
     * @param species порода дерева
     * @param color   цвет листвы/хвои
     * @param texture текстура коры
     */
    public void plantTree(int x, int y, String species, String color, String texture) {
        TreeType type = TreeTypeFactory.getTreeType(species, color, texture);
        trees.add(new Tree(x, y, type));
    }

    /**
     * Отрисовывает все деревья в лесу.
     */
    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }

    /**
     * Возвращает общее количество деревьев в лесу.
     *
     * @return количество деревьев
     */
    public int getTreeCount() {
        return trees.size();
    }
}
