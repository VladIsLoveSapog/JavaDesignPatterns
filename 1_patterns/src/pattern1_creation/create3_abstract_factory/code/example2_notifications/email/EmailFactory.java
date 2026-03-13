package pattern1_creation.create3_abstract_factory.code.example2_notifications.email;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.AbstractNotificationFactory;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationBody;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationHeader;

/**
 * Фабрика email-уведомлений.
 *
 * <p>Реализует {@link AbstractNotificationFactory} и создаёт все продукты
 * для канала Email: {@link EmailHeader} и {@link EmailBody}. Сигнатуры методов
 * возвращают абстрактные типы, что обеспечивает слабую связанность
 * с клиентским кодом.</p>
 *
 * @see AbstractNotificationFactory
 */
public class EmailFactory implements AbstractNotificationFactory {

    @Override
    public NotificationHeader createHeader(String recipient) {
        return new EmailHeader(recipient);
    }

    @Override
    public NotificationBody createBody(String recipient, String message) {
        return new EmailBody(recipient, message);
    }
}
