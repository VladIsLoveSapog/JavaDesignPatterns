package pattern2_structural.struct7_flyweight.code.example1_menu;

/**
 * Конкретный легковес (Concrete Flyweight) — пицца в меню ресторана.
 *
 * <p>Пицца является позицией меню и реализует интерфейс {@link MenuEntry}.
 * Поле {@code name} представляет собой <b>внутреннее (разделяемое) состояние</b> —
 * оно неизменяемо ({@code final}) и одинаково для всех заказов одной и той же пиццы.
 *
 * <p>Внешнее состояние (номер стола, к которому подаётся блюдо) передаётся
 * в метод {@link #serve(Integer)} в качестве параметра и не хранится внутри легковеса.
 */
public class Pizza implements MenuEntry {
    private final String name;

    /**
     * Создаёт экземпляр пиццы с указанным названием.
     *
     * @param name название пиццы (внутреннее состояние)
     */
    public Pizza(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void serve(Integer tableNumber) {
        System.out.println("Pizza " + name + " is served to table " + tableNumber + ".");
    }
}
