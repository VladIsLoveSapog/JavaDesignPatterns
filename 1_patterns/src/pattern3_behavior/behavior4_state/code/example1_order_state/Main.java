package pattern3_behavior.behavior4_state.code.example1_order_state;

/**
 * Демонстрация паттерна «Состояние» (State) на примере отслеживания посылки.
 *
 * <p>Незамысловатый пример, в котором посылка проходит через линейную цепочку состояний:
 * <pre>
 *     OrderedState → DeliveredState → ReceivedState
 * </pre>
 *
 * <p>В данном примере количество состояний и их порядок определены заранее.
 * Состояния связываются друг с другом внутри собственного класса, а не в клиентском коде.
 * Контекст ({@link Package}) лишь делегирует вызовы текущему объекту-состоянию.</p>
 *
 * <p>Сценарий демонстрации:</p>
 * <ol>
 *     <li>Создаётся посылка в начальном состоянии «Заказана» ({@link OrderedState});</li>
 *     <li>Посылка переводится в состояние «Доставлена» ({@link DeliveredState});</li>
 *     <li>Посылка переводится в состояние «Получена» ({@link ReceivedState});</li>
 *     <li>Попытка перейти дальше — выводится сообщение о невозможности перехода.</li>
 * </ol>
 *
 * <p>Ожидаемый вывод:</p>
 * <pre>
 *     Package ordered, not delivered to the office yet.
 *     Package delivered to post office, not received yet.
 *     Package received.
 *     This package is already received by a client.
 * </pre>
 *
 * @see Package
 * @see PackageState
 * @see OrderedState
 * @see DeliveredState
 * @see ReceivedState
 */
public class Main {
    public static void main(String[] args) {
        // Создаём посылку — начальное состояние: «Заказана» (OrderedState)
        Package pkg = new Package();
        pkg.printStatus();      // Package ordered, not delivered to the office yet.

        // Переводим в следующее состояние: «Доставлена» (DeliveredState)
        pkg.nextState();
        pkg.printStatus();      // Package delivered to post office, not received yet.

        // Переводим в следующее состояние: «Получена» (ReceivedState)
        pkg.nextState();
        pkg.printStatus();      // Package received.

        // Попытка перейти дальше — посылка уже получена, переход невозможен
        pkg.nextState();
        pkg.printStatus();      // This package is already received by a client.
    }
}
