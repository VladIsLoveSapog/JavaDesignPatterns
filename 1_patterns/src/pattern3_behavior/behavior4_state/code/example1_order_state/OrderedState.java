package pattern3_behavior.behavior4_state.code.example1_order_state;

/**
 * Состояние «Заказана» — роль <b>ConcreteState</b> (Конкретное состояние) в паттерне «Состояние» (State).
 *
 * <p>Данное состояние является <b>начальным</b> в линейной цепочке переходов посылки:
 * <pre>
 *     [OrderedState] → DeliveredState → ReceivedState
 * </pre>
 *
 * <p>Поведение переходов:</p>
 * <ul>
 *     <li>{@link #next(Package)} — переводит посылку в состояние «Доставлена» ({@link DeliveredState});</li>
 *     <li>{@link #prev(Package)} — не выполняет переход, так как данное состояние является начальным
 *         и предыдущего состояния не существует.</li>
 * </ul>
 *
 * <p>Каждое конкретное состояние самостоятельно определяет, в какое состояние должна перейти
 * посылка при вызове {@code next()} или {@code prev()}, что позволяет инкапсулировать
 * логику переходов и избежать условных конструкций в контексте ({@link Package}).</p>
 *
 * @see PackageState
 * @see Package
 * @see DeliveredState
 * @see ReceivedState
 */
public class OrderedState implements PackageState {

    /**
     * Переход к следующему состоянию — «Доставлена» ({@link DeliveredState}).
     *
     * <p>Устанавливает контексту новое состояние {@link DeliveredState}, означающее,
     * что посылка доставлена в почтовое отделение.</p>
     *
     * @param pkg посылка (контекст), которая будет переведена в следующее состояние.
     */
    @Override
    public void next(Package pkg) {
        // Переводим посылку в состояние «Доставлена»
        pkg.setState(new DeliveredState());
    }

    /**
     * Попытка перехода к предыдущему состоянию.
     *
     * <p>У данного состояния нет предыдущего, так как оно является начальным
     * в цепочке. Вместо перехода выводится информационное сообщение.</p>
     *
     * @param pkg посылка (контекст), которая будет переведена в предыдущее состояние.
     */
    @Override
    public void prev(Package pkg) {
        // Начальное состояние — переход назад невозможен
        System.out.println("The package is in its root state.");
    }

    /**
     * Вывод информации о текущем статусе посылки.
     *
     * <p>Сообщает, что посылка заказана, но ещё не доставлена в почтовое отделение.</p>
     */
    @Override
    public void printStatus() {
        System.out.println("Package ordered, not delivered to the office yet.");
    }
}
