package pattern3_behavior.behavior7_visitor.code.example2_figure;

/**
 * Конкретный элемент — круг (ConcreteElement в паттерне Visitor).
 *
 * <p>Хранит радиус и реализует {@link Element#accept(Visitor)},
 * вызывая {@code visitor.visitCircle(this)} — вторая часть
 * двойной диспетчеризации.
 */
public class Circle implements Element {

    /** Радиус круга. */
    private double radius;

    /**
     * Создаёт круг с заданным радиусом.
     *
     * @param radius радиус круга
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * @return радиус круга
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Принимает посетителя, направляя вызов в {@code visitCircle}.
     *
     * @param visitor посетитель, выполняющий операцию
     */
    @Override
    public void accept(Visitor visitor) {
        // Элемент сам вызывает нужный метод посетителя — двойная диспетчеризация
        visitor.visitCircle(this);
    }
}
