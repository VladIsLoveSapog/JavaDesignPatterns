package pattern3_behavior.behavior7_visitor.code.example2_figure;

/**
 * Конкретный посетитель — печать информации о фигурах.
 *
 * <p>Реализует {@link Visitor} и для каждого типа фигуры выводит
 * её параметры в консоль. Это отдельная операция, вынесенная из
 * классов фигур согласно паттерну Посетитель.
 *
 * <p><b>Роль в паттерне:</b> конкретный посетитель (ConcreteVisitor).
 * Если потребуется новая операция (например, вычисление площади),
 * достаточно создать нового посетителя — классы фигур изменять не нужно.
 */
public class PrintVisitor implements Visitor {

    /**
     * Выводит информацию о круге: его радиус.
     *
     * @param circle круг для печати
     */
    @Override
    public void visitCircle(Circle circle) {
        System.out.println("Circle with radius: " + circle.getRadius());
    }

    /**
     * Выводит информацию о прямоугольнике: ширину и высоту.
     *
     * @param rectangle прямоугольник для печати
     */
    @Override
    public void visitRectangle(Rectangle rectangle) {
        System.out.println("Rectangle with width: " + rectangle.getWidth() +
                " and height: " + rectangle.getHeight());
    }
}
