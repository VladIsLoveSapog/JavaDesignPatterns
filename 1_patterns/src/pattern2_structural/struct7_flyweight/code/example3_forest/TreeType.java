package pattern2_structural.struct7_flyweight.code.example3_forest;

/**
 * Легковес (Flyweight) — тип дерева в лесу.
 *
 * <p>Хранит <b>внутреннее (разделяемое) состояние</b> дерева:
 * <ul>
 *     <li>{@code species} — порода (например, "Берёза", "Ель")</li>
 *     <li>{@code color} — цвет листвы/хвои</li>
 *     <li>{@code texture} — текстура коры</li>
 * </ul>
 * Все поля неизменяемы ({@code final}). Один экземпляр {@code TreeType}
 * разделяется между тысячами деревьев одной породы в лесу.
 *
 * <p>Внешнее состояние (координаты x, y) передаётся в метод {@link #draw(int, int)}
 * в качестве параметров и не хранится внутри легковеса.
 */
public class TreeType {
    private final String species;
    private final String color;
    private final String texture;

    /**
     * Создаёт тип дерева с указанными характеристиками.
     *
     * @param species  порода дерева
     * @param color    цвет листвы/хвои
     * @param texture  текстура коры
     */
    public TreeType(String species, String color, String texture) {
        this.species = species;
        this.color = color;
        this.texture = texture;
    }

    /**
     * Отрисовывает дерево данного типа в указанных координатах.
     *
     * <p>Объединяет внутреннее состояние (порода, цвет, текстура)
     * с внешним состоянием (координаты x, y), переданным параметрами.
     *
     * @param x координата по горизонтали (внешнее состояние)
     * @param y координата по вертикали (внешнее состояние)
     */
    public void draw(int x, int y) {
        System.out.println("Drawing tree [" + species + ", " + color + ", " + texture + "] at (" + x + ", " + y + ")");
    }

    /**
     * Возвращает породу дерева.
     *
     * @return порода
     */
    public String getSpecies() {
        return species;
    }
}
