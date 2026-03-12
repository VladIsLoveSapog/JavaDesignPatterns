package pattern1_creation.create2_factory_method.code.example3_external;

/**
 * Внешняя фабрика — отдельный класс только со статическими фабричными методами.
 *
 * Этот класс полностью отделён от иерархии продуктов (Shape, Circle, Rectangle).
 * Его единственная ответственность — создание объектов-фигур.
 *
 * Это самый простой и часто встречаемый вариант: один утилитный класс,
 * несколько статических методов с говорящими именами.
 *
 * Использование: ShapeFactory.createCircle(5.0)
 *               ShapeFactory.createRectangle(4.0, 6.0)
 *               ShapeFactory.createSquare(5.0)
 */
public class ShapeFactory {

    /**
     * Приватный конструктор — этот класс не предназначен для создания объектов.
     * Все методы статические, экземпляр не нужен.
     */
    private ShapeFactory() {
        // Утилитный класс — не создаём экземпляры
    }

    /**
     * Фабричный метод: создать круг с заданным радиусом.
     *
     * @param radius радиус круга (должен быть > 0)
     * @return новый объект Circle
     */
    public static Shape createCircle(double radius) {
        // Можно добавить валидацию здесь, не трогая класс Circle
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус должен быть положительным, получено: " + radius);
        }
        return new Circle(radius);
    }

    /**
     * Фабричный метод: создать прямоугольник с заданными размерами.
     *
     * @param width  ширина (должна быть > 0)
     * @param height высота (должна быть > 0)
     * @return новый объект Rectangle
     */
    public static Shape createRectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Ширина и высота должны быть положительными.");
        }
        return new Rectangle(width, height);
    }

    /**
     * Фабричный метод: создать квадрат (частный случай прямоугольника).
     *
     * Этот метод показывает, как фабрика может предоставлять удобные
     * специализированные методы поверх базовых конструкторов продуктов.
     *
     * @param side сторона квадрата (должна быть > 0)
     * @return новый объект Rectangle с равными сторонами
     */
    public static Shape createSquare(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительной.");
        }
        // Квадрат — это прямоугольник со сторонами одинаковой длины
        return new Rectangle(side, side);
    }
}
