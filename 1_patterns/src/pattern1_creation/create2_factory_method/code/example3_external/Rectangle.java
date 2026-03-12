package pattern1_creation.create2_factory_method.code.example3_external;

/**
 * Конкретный продукт — прямоугольник.
 *
 * Реализует интерфейс Shape. Создаётся фабрикой ShapeFactory.
 */
public class Rectangle implements Shape {

    /** Ширина прямоугольника */
    private final double width;

    /** Высота прямоугольника */
    private final double height;

    /**
     * Конструктор прямоугольника.
     *
     * @param width  ширина
     * @param height высота
     */
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Выводит описание прямоугольника с его размерами.
     */
    @Override
    public void draw() {
        System.out.println("Нарисован прямоугольник " + width + " x " + height);
    }

    /**
     * Вычисляет площадь прямоугольника по формуле ширина * высота.
     *
     * @return площадь прямоугольника
     */
    @Override
    public double area() {
        return width * height;
    }
}
