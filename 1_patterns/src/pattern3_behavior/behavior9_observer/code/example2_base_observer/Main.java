package pattern3_behavior.behavior9_observer.code.example2_base_observer;

/**
 * <b>Демонстрация работы паттерна Observer</b>
 *
 * <p>Класс содержит точку входа, в которой показан базовый сценарий
 * использования паттерна <i>Наблюдатель (Observer)</i>:</p>
 * <ol>
 *     <li>Создаётся субъект ({@link Subject})</li>
 *     <li>Создаются конкретные наблюдатели ({@link ConcreteObserver})</li>
 *     <li>Наблюдатели подписываются на субъект через
 *         {@link Subject#addObserver(Observer)}</li>
 *     <li>При изменении состояния субъекта ({@link Subject#setState(String)})
 *         все подписчики автоматически получают уведомление</li>
 * </ol>
 *
 * <p><b>Ожидаемый вывод:</b></p>
 * <pre>
 *     Наблюдатель 1 получил сообщение: Изменение состояния!
 *     Наблюдатель 2 получил сообщение: Изменение состояния!
 * </pre>
 *
 * @see Observer
 * @see Subject
 * @see ConcreteObserver
 */
public class Main {

    /**
     * Точка входа в программу.
     *
     * <p>Создаёт экземпляр {@link Subject}, регистрирует двух наблюдателей
     * и изменяет состояние субъекта, что приводит к автоматической рассылке
     * уведомлений всем подписчикам.</p>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer observer1 = new ConcreteObserver("Наблюдатель 1");
        Observer observer2 = new ConcreteObserver("Наблюдатель 2");

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.setState("Изменение состояния!");
    }
}
