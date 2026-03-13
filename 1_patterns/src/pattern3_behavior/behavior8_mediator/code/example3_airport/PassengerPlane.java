package pattern3_behavior.behavior8_mediator.code.example3_airport;

/**
 * Конкретный коллега (Concrete Colleague) — пассажирский самолёт.
 *
 * <p>Реализует получение уведомлений с пометкой "[Пассажирский]",
 * чтобы в консоли было видно, какой тип самолёта получил сообщение.
 *
 * <p><b>Роль в паттерне:</b> Concrete Colleague.
 */
public class PassengerPlane extends Aircraft {

    /**
     * @param callSign позывной пассажирского самолёта
     */
    public PassengerPlane(String callSign) {
        super(callSign);
    }

    /**
     * Получает уведомление от башни с пометкой типа самолёта.
     *
     * @param message текст уведомления от диспетчерской башни
     */
    @Override
    public void receiveNotification(String message) {
        System.out.println("[Пассажирский] " + getCallSign() + " получил уведомление: " + message);
    }
}
