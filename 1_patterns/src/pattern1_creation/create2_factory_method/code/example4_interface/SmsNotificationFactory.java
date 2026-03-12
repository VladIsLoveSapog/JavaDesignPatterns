package pattern1_creation.create2_factory_method.code.example4_interface;

/**
 * Конкретный создатель — фабрика SMS-уведомлений.
 *
 * Реализует интерфейс NotificationFactory.
 * Переопределяет фабричный метод createNotification(),
 * возвращая объект SmsNotification.
 */
public class SmsNotificationFactory implements NotificationFactory {

    /**
     * Фабричный метод: создаёт SMS-уведомление.
     *
     * @return новый объект SmsNotification
     */
    @Override
    public Notification createNotification() {
        return new SmsNotification();
    }
}
