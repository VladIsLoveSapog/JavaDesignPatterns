package pattern2_structural.struct3_bridge.code.example1_shapes;

/**
 * Красный цвет — <b>Concrete Implementation</b> (Конкретная реализация)
 * в паттерне Bridge.
 *
 * <p>Реализует интерфейс {@link Color}, предоставляя конкретную логику
 * заливки красным цветом. Может быть передана в любую фигуру
 * ({@link Rectangle}, {@link Triangle} и т.д.) через конструктор
 * {@link Shape#Shape(Color)}.
 *
 * <p><b>Роль в паттерне Bridge:</b> конкретная реализация находится
 * на «другой стороне моста» от абстракции. Она ничего не знает
 * о фигурах — только о том, как выполнить заливку определённым цветом.
 * Благодаря этому новые цвета можно добавлять без изменения иерархии
 * фигур, и наоборот.
 *
 * @see Color
 * @see Shape
 */
public class RedColor implements Color {

    /**
     * Выполняет заливку красным цветом.
     *
     * <p>Вызывается из метода {@link Shape#draw()} через ссылку
     * {@link Shape#color}, образуя делегирование через «мост».
     */
    @Override
    public void fillColor() {
        System.out.println("Filling in red color");
    }
}
