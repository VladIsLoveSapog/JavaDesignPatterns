package pattern1_creation.create2_factory_method.code.example4_interface;

/**
 * Конкретный создатель — фабрика email-уведомлений.
 *
 * Реализует интерфейс NotificationFactory.
 * Переопределяет фабричный метод createNotification(),
 * возвращая объект EmailNotification.
 */
public class EmailNotificationFactory implements NotificationFactory {

    /**
     * Фабричный метод: создаёт email-уведомление.
     *
     * @return новый объект EmailNotification
     */
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
