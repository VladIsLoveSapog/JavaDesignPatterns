package pattern3_behavior.behavior7_visitor.code.example2_figure;

/**
 * Интерфейс элемента-фигуры (Element в паттерне Visitor).
 *
 * <p>Каждая геометрическая фигура ({@link Circle}, {@link Rectangle})
 * реализует этот интерфейс, предоставляя метод {@link #accept(Visitor)}
 * для приёма посетителя.
 *
 * <p><b>Двойная диспетчеризация:</b> метод {@code accept} позволяет
 * элементу самому определить, какой метод посетителя будет вызван,
 * передавая {@code this} в соответствующий {@code visit}-метод.
 */
public interface Element {

    /**
     * Принимает посетителя и делегирует ему выполнение операции.
     *
     * @param visitor посетитель, выполняющий операцию над фигурой
     */
    void accept(Visitor visitor);
}
