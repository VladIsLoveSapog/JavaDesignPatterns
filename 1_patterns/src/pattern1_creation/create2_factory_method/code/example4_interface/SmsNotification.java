package pattern1_creation.create2_factory_method.code.example4_interface;

/**
 * Конкретный продукт — SMS-уведомление.
 *
 * Реализует интерфейс Notification.
 * Создаётся фабрикой SmsNotificationFactory.
 */
public class SmsNotification implements Notification {

    /**
     * Отправляет SMS на указанный номер телефона.
     *
     * @param recipient номер телефона получателя
     * @param message   текст SMS
     */
    @Override
    public void send(String recipient, String message) {
        System.out.println("[SMS] Отправка сообщения на номер: " + recipient);
        System.out.println("  Текст: " + message);
        System.out.println("  Статус: SMS отправлено через SMS-шлюз.");
    }
}
