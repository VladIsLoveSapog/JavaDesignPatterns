package pattern3_behavior.behavior5_memento.code.example3_drawing_editor;

/**
 * Неизменяемая фигура на холсте.
 *
 * <p>Содержит тип фигуры, координаты и цвет. Все поля {@code final},
 * поэтому объект можно безопасно передавать между снимками без
 * дополнительного копирования.</p>
 *
 * <p>Роль в паттерне: <b>объект-значение</b>, из которых состоит
 * внутреннее состояние {@link Canvas Создателя}.</p>
 */
public class Shape {
    /** Тип фигуры (например, "Circle", "Rectangle"). */
    private final String type;

    /** Координата X на холсте. */
    private final int x;

    /** Координата Y на холсте. */
    private final int y;

    /** Цвет фигуры. */
    private final String color;

    /**
     * Создаёт фигуру с указанными параметрами.
     *
     * @param type  тип фигуры
     * @param x     координата X
     * @param y     координата Y
     * @param color цвет фигуры
     */
    public Shape(String type, int x, int y, String color) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Возвращает тип фигуры.
     *
     * @return тип фигуры
     */
    public String getType() {
        return type;
    }

    /**
     * Возвращает координату X.
     *
     * @return координата X
     */
    public int getX() {
        return x;
    }

    /**
     * Возвращает координату Y.
     *
     * @return координата Y
     */
    public int getY() {
        return y;
    }

    /**
     * Возвращает цвет фигуры.
     *
     * @return цвет
     */
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return type + "(" + x + ", " + y + ", " + color + ")";
    }
}
