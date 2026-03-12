package pattern1_creation.create2_factory_method.code.example3_external;

/**
 * Конкретный продукт — круг.
 *
 * Реализует интерфейс Shape. Создаётся фабрикой ShapeFactory.
 */
public class Circle implements Shape {

    /** Радиус круга */
    private final double radius;

    /**
     * Конструктор круга.
     *
     * @param radius радиус (должен быть положительным)
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Выводит описание круга с его радиусом.
     */
    @Override
    public void draw() {
        System.out.println("Нарисован круг с радиусом " + radius);
    }

    /**
     * Вычисляет площадь круга по формуле π * r².
     *
     * @return площадь круга
     */
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
