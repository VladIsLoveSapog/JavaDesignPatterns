package pattern3_behavior.behavior7_visitor.code.example2_figure;

import java.util.List;

/**
 * Демонстрация паттерна Посетитель на примере обхода геометрических фигур.
 *
 * <p>Создаётся список фигур ({@link Circle}, {@link Rectangle}),
 * и к каждой из них применяется посетитель {@link PrintVisitor},
 * который выводит информацию о фигуре.
 *
 * <p><b>Ключевой момент:</b> клиентский код работает с фигурами через
 * общий интерфейс {@link Element} и не использует {@code instanceof} —
 * правильный метод посетителя вызывается автоматически благодаря
 * двойной диспетчеризации.
 */
public class Main {
    public static void main(String[] args) {
        // Создаём коллекцию фигур разных типов
        List<Element> shapes = List.of(
                new Circle(5.0),
                new Rectangle(4.0, 6.0),
                new Circle(3.5),
                new Rectangle(2.0, 3.0)
        );

        // Создаём посетителя для печати информации
        PrintVisitor printer = new PrintVisitor();

        // Обходим все фигуры: каждая сама вызывает нужный метод посетителя
        for (Element shape : shapes) {
            shape.accept(printer);
        }
    }
}
