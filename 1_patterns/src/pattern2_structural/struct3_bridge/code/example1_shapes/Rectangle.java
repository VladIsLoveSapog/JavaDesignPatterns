package pattern2_structural.struct3_bridge.code.example1_shapes;

/**
 * Прямоугольник — <b>Refined Abstraction</b> (Уточнённая абстракция)
 * в паттерне Bridge.
 *
 * <p>Расширяет базовую абстракцию {@link Shape}, реализуя конкретную
 * логику отрисовки прямоугольника. При этом детали раскраски
 * по-прежнему делегируются объекту {@link Color} через вызов
 * {@link Color#fillColor()}.
 *
 * <p><b>Роль в паттерне Bridge:</b> уточнённая абстракция добавляет
 * специфику конкретной фигуры, но не привязывается к конкретному цвету.
 * Благодаря этому {@code Rectangle} можно создать с любым цветом
 * ({@link RedColor}, {@link GreenColor}, {@link BlackColor}), не изменяя
 * код самого класса.
 *
 * @see Shape
 * @see Color
 */
public class Rectangle extends Shape {

    /**
     * Создаёт прямоугольник с заданным цветом.
     *
     * @param color реализация цвета, которая будет использоваться
     *              при отрисовке данного прямоугольника
     */
    public Rectangle(Color color) {
        super(color);
    }

    /**
     * Отрисовывает прямоугольник и заливает его цветом.
     *
     * <p>Сначала выводит информацию о типе фигуры, затем делегирует
     * заливку объекту {@link #color} — это и есть «мост» к реализации.
     */
    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
        color.fillColor();
    }
}
