package pattern3_behavior.behavior4_state.code.example1_order_state;

/**
 * Состояние «Получена» — роль <b>ConcreteState</b> (Конкретное состояние) в паттерне «Состояние» (State).
 *
 * <p>Данное состояние является <b>конечным (терминальным)</b> в линейной цепочке переходов посылки:
 * <pre>
 *     OrderedState → DeliveredState → [ReceivedState]
 * </pre>
 *
 * <p>Поведение переходов:</p>
 * <ul>
 *     <li>{@link #next(Package)} — не выполняет переход, так как данное состояние является конечным
 *         и последующего состояния не существует;</li>
 *     <li>{@link #prev(Package)} — возвращает посылку в состояние «Доставлена» ({@link DeliveredState}).</li>
 * </ul>
 *
 * <p>Терминальное состояние означает, что посылка доставлена и получена клиентом.
 * Дальнейший переход вперёд невозможен, однако возврат к предыдущему состоянию
 * предусмотрен для гибкости модели.</p>
 *
 * @see PackageState
 * @see Package
 * @see OrderedState
 * @see DeliveredState
 */
public class ReceivedState implements PackageState {

    /**
     * Попытка перехода к следующему состоянию.
     *
     * <p>У данного состояния нет последующего, так как посылка уже доставлена
     * и получена клиентом. Вместо перехода выводится информационное сообщение.</p>
     *
     * @param pkg посылка (контекст), которая будет переведена в следующее состояние.
     */
    @Override
    public void next(Package pkg) {
        // Конечное состояние — переход вперёд невозможен
        System.out.println("This package is already received by a client.");
    }

    /**
     * Переход к предыдущему состоянию — «Доставлена» ({@link DeliveredState}).
     *
     * <p>Возвращает посылку в состояние {@link DeliveredState}, означающее,
     * что посылка снова находится на этапе доставки.</p>
     *
     * @param pkg посылка (контекст), которая будет переведена в предыдущее состояние.
     */
    @Override
    public void prev(Package pkg) {
        // Возвращаем посылку в состояние «Доставлена»
        pkg.setState(new DeliveredState());
    }

    /**
     * Вывод информации о текущем статусе посылки.
     *
     * <p>Сообщает, что посылка получена клиентом.</p>
     */
    @Override
    public void printStatus() {
        System.out.println("Package received.");
    }
}
