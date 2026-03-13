package pattern2_structural.struct7_flyweight.code.example3_forest;

/**
 * Контекст (Context) — конкретное дерево в лесу.
 *
 * <p>Хранит <b>внешнее (неразделяемое) состояние</b> — координаты дерева ({@code x}, {@code y}),
 * а также ссылку на легковес {@link TreeType}, содержащий внутреннее состояние
 * (породу, цвет, текстуру).
 *
 * <p>Метод {@link #draw()} делегирует отрисовку легковесу, передавая ему координаты
 * в качестве параметров. Это позволяет тысячам деревьев одной породы
 * разделять один объект {@link TreeType}.
 */
public class Tree {
    private final int x;
    private final int y;
    private final TreeType type;

    /**
     * Создаёт дерево в указанных координатах с заданным типом.
     *
     * @param x    координата по горизонтали (внешнее состояние)
     * @param y    координата по вертикали (внешнее состояние)
     * @param type тип дерева (ссылка на легковес)
     */
    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Отрисовывает дерево, делегируя вызов легковесу {@link TreeType#draw(int, int)}.
     */
    public void draw() {
        type.draw(x, y);
    }
}
