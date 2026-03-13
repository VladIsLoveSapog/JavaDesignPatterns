package pattern2_structural.struct7_flyweight.code.example1_menu;

/**
 * Конкретный легковес (Concrete Flyweight) — салат в меню ресторана.
 *
 * <p>Салат является позицией меню и реализует интерфейс {@link MenuEntry}.
 * Поле {@code name} представляет собой <b>внутреннее (разделяемое) состояние</b> —
 * оно неизменяемо ({@code final}) и одинаково для всех заказов одного и того же салата.
 *
 * <p>Внешнее состояние (номер стола, к которому подаётся блюдо) передаётся
 * в метод {@link #serve(Integer)} в качестве параметра и не хранится внутри легковеса.
 */
public class Salad implements MenuEntry {
    private final String name;

    /**
     * Создаёт экземпляр салата с указанным названием.
     *
     * @param name название салата (внутреннее состояние)
     */
    public Salad(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void serve(Integer tableNumber) {
        System.out.println("Salad " + name + " is served to table " + tableNumber + ".");
    }
}
