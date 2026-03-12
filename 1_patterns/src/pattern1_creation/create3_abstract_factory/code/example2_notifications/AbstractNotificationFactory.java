package pattern1_creation.create3_abstract_factory.code.example2_notifications;

/**
 * [AbstractFactory]
 * Абстрактная фабрика уведомлений.
 *
 * <p>Наличие обоих методов в одном интерфейсе гарантирует согласованность:
 * нельзя получить email-заголовок вместе с sms-телом — фабрика всегда
 * создаёт продукты одного семейства.
 */
public interface AbstractNotificationFactory {

    /**
     * Создаёт заголовок уведомления, соответствующий каналу.
     *
     * @param recipient получатель
     * @return заголовок уведомления
     */
    NotificationHeader createHeader(String recipient);

    /**
     * Создаёт тело уведомления, соответствующее каналу.
     *
     * @param recipient получатель
     * @param message   текст сообщения
     * @return тело уведомления
     */
    NotificationBody createBody(String recipient, String message);
}
