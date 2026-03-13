package pattern1_creation.create6_multiton.code.example1_shape_factory;

import pattern1_creation.create6_multiton.code.example1_shape_factory.shape.Shape;

/**
 * Демонстрация использования паттерна Мультитон на примере фабрик фигур.
 *
 * <p>Показывает основные сценарии работы с {@link ShapeFactoryMultiton}:</p>
 * <ul>
 *   <li>Получение фабрик для различных типов фигур через мультитон</li>
 *   <li>Создание конкретных фигур с помощью полученных фабрик</li>
 *   <li>Проверка того, что повторное обращение к мультитону с тем же ключом
 *       возвращает тот же самый экземпляр фабрики (оператор {@code ==})</li>
 * </ul>
 */
public class ShapeFactoryMain {
    public static void main(String[] args) {
        // Получение фабрик через мультитон
        ShapeFactory circleFactory = ShapeFactoryMultiton.getInstance(ShapeType.CIRCLE);
        ShapeFactory rectangleFactory = ShapeFactoryMultiton.getInstance(ShapeType.RECTANGLE);
        ShapeFactory triangleFactory = ShapeFactoryMultiton.getInstance(ShapeType.TRIANGLE);

        // Создание фигур через фабрики
        Shape circle = circleFactory.createShape("Большой круг");
        Shape rectangle = rectangleFactory.createShape("Маленький прямоугольник");
        Shape triangle = triangleFactory.createShape("Равносторонний треугольник");

        // Отрисовка фигур
        circle.draw();
        rectangle.draw();
        triangle.draw();

        // Проверка единственности экземпляра фабрики для каждого типа
        ShapeFactory anotherCircleFactory = ShapeFactoryMultiton.getInstance(ShapeType.CIRCLE);
        System.out.println("\nФабрики для CIRCLE — один и тот же объект: "
                                   + (circleFactory == anotherCircleFactory));
    }
}
