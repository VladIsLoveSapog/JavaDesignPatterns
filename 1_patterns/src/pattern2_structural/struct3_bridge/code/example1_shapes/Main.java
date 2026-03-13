package pattern2_structural.struct3_bridge.code.example1_shapes;

/**
 * Демонстрация паттерна <b>Bridge</b> на примере фигур и цветов.
 *
 * <p>Показывает, как абстракция ({@link Shape}) и реализация ({@link Color})
 * развиваются независимо друг от друга. Любую фигуру можно комбинировать
 * с любым цветом без создания отдельного подкласса для каждой пары
 * «фигура + цвет».
 *
 * <p><b>Без паттерна Bridge</b> для {@code N} фигур и {@code M} цветов
 * потребовалось бы {@code N * M} классов (например,
 * {@code RedRectangle}, {@code GreenTriangle} и т.д.).
 * С паттерном Bridge достаточно {@code N + M} классов.
 */
public class Main {
    public static void main(String[] args) {
        // Создаём прямоугольник с красным цветом — «мост» связывает Shape и Color
        Shape rect = new Rectangle(new RedColor());
        rect.draw();    // draw() делегирует заливку объекту RedColor

        System.out.println("---------------");

        // Создаём треугольник с зелёным цветом — другая комбинация без нового класса
        Shape triangle = new Triangle(new GreenColor());
        triangle.draw();    // draw() делегирует заливку объекту GreenColor
    }
}
