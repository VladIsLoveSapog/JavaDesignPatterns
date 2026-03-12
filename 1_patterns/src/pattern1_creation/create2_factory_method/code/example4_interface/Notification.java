package pattern1_creation.create2_factory_method.code.example4_interface;

/**
 * Интерфейс продукта — уведомление.
 *
 * Все конкретные уведомления реализуют этот интерфейс.
 * Фабрики и клиентский код работают только с этим типом.
 */
public interface Notification {

    /**
     * Отправить уведомление получателю.
     *
     * @param recipient адресат (email, номер телефона и т.д.)
     * @param message   текст уведомления
     */
    public void send(String recipient, String message);
}
