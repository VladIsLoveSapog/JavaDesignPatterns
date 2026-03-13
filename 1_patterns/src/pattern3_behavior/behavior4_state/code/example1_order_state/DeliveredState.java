package pattern3_behavior.behavior4_state.code.example1_order_state;

/**
 * Состояние «Доставлена» — роль <b>ConcreteState</b> (Конкретное состояние) в паттерне «Состояние» (State).
 *
 * <p>Данное состояние является <b>промежуточным</b> в линейной цепочке переходов посылки:
 * <pre>
 *     OrderedState → [DeliveredState] → ReceivedState
 * </pre>
 *
 * <p>Поведение переходов:</p>
 * <ul>
 *     <li>{@link #next(Package)} — переводит посылку в состояние «Получена» ({@link ReceivedState});</li>
 *     <li>{@link #prev(Package)} — возвращает посылку в состояние «Заказана» ({@link OrderedState}).</li>
 * </ul>
 *
 * <p>Как промежуточное состояние, {@code DeliveredState} поддерживает переходы в обе стороны:
 * и вперёд (к получению), и назад (к заказу). Это позволяет моделировать ситуации,
 * когда посылка может быть возвращена на предыдущий этап обработки.</p>
 *
 * @see PackageState
 * @see Package
 * @see OrderedState
 * @see ReceivedState
 */
public class DeliveredState implements PackageState {

    /**
     * Переход к следующему состоянию — «Получена» ({@link ReceivedState}).
     *
     * <p>Устанавливает контексту новое состояние {@link ReceivedState}, означающее,
     * что посылка получена клиентом.</p>
     *
     * @param pkg посылка (контекст), которая будет переведена в следующее состояние.
     */
    @Override
    public void next(Package pkg) {
        // Переводим посылку в состояние «Получена»
        pkg.setState(new ReceivedState());
    }

    /**
     * Переход к предыдущему состоянию — «Заказана» ({@link OrderedState}).
     *
     * <p>Возвращает посылку в состояние {@link OrderedState}, означающее,
     * что посылка снова находится на этапе заказа.</p>
     *
     * @param pkg посылка (контекст), которая будет переведена в предыдущее состояние.
     */
    @Override
    public void prev(Package pkg) {
        // Возвращаем посылку в состояние «Заказана»
        pkg.setState(new OrderedState());
    }

    /**
     * Вывод информации о текущем статусе посылки.
     *
     * <p>Сообщает, что посылка доставлена в почтовое отделение, но ещё не получена клиентом.</p>
     */
    @Override
    public void printStatus() {
        System.out.println("Package delivered to post office, not received yet.");
    }
}
