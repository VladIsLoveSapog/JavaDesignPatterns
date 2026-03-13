package pattern1_creation.create3_abstract_factory.code.example2_notifications;

/**
 * Абстрактная фабрика уведомлений.
 *
 * <p>Определяет контракт для создания семейства связанных продуктов:
 * {@link NotificationHeader} и {@link NotificationBody}. Наличие обоих методов
 * в одном интерфейсе гарантирует согласованность — нельзя получить email-заголовок
 * вместе с sms-телом, фабрика всегда создаёт продукты одного семейства.</p>
 *
 * <h3>Пример использования</h3>
 * <pre>{@code
 * AbstractNotificationFactory factory = new EmailFactory();
 * NotificationHeader header = factory.createHeader("user@example.com");
 * NotificationBody body = factory.createBody("user@example.com", "Привет!");
 * }</pre>
 *
 * @see NotificationHeader
 * @see NotificationBody
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
