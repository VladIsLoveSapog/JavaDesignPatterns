package pattern3_behavior.behavior7_visitor.code.example2_figure;

/**
 * Конкретный элемент — прямоугольник (ConcreteElement в паттерне Visitor).
 *
 * <p>Хранит ширину и высоту. Реализует {@link Element#accept(Visitor)},
 * вызывая {@code visitor.visitRectangle(this)}.
 */
public class Rectangle implements Element {

    /** Ширина прямоугольника. */
    private double width;

    /** Высота прямоугольника. */
    private double height;

    /**
     * Создаёт прямоугольник с заданными размерами.
     *
     * @param width  ширина
     * @param height высота
     */
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * @return ширина прямоугольника
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return высота прямоугольника
     */
    public double getHeight() {
        return height;
    }

    /**
     * Принимает посетителя, направляя вызов в {@code visitRectangle}.
     *
     * @param visitor посетитель, выполняющий операцию
     */
    @Override
    public void accept(Visitor visitor) {
        // Элемент сам вызывает нужный метод посетителя — двойная диспетчеризация
        visitor.visitRectangle(this);
    }
}
