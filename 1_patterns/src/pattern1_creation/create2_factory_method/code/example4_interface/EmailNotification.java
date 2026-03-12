package pattern1_creation.create2_factory_method.code.example4_interface;


/**
 * Конкретный продукт — уведомление по электронной почте.
 *
 * Реализует интерфейс Notification.
 * Создаётся фабрикой EmailNotificationFactory.
 */
public class EmailNotification implements Notification {

    /**
     * Отправляет уведомление по email.
     *
     * @param recipient email-адрес получателя
     * @param message   текст письма
     */
    @Override
    public void send(String recipient, String message) {
        System.out.println("[Email] Отправка письма на адрес: " + recipient);
        System.out.println("  Текст: " + message);
        System.out.println("  Статус: письмо отправлено через SMTP-сервер.");
    }
}
