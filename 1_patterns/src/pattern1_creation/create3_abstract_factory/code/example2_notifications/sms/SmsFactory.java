package pattern1_creation.create3_abstract_factory.code.example2_notifications.sms;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.AbstractNotificationFactory;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationBody;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationHeader;

/**
 * Фабрика SMS-уведомлений.
 *
 * <p>Реализует {@link AbstractNotificationFactory} и создаёт все продукты
 * для канала SMS: {@link SmsHeader} и {@link SmsBody}. Сигнатуры методов
 * возвращают абстрактные типы, что обеспечивает слабую связанность
 * с клиентским кодом.</p>
 *
 * @see AbstractNotificationFactory
 */
public class SmsFactory implements AbstractNotificationFactory {

    @Override
    public NotificationHeader createHeader(String recipient) {
        return new SmsHeader(recipient);
    }

    @Override
    public NotificationBody createBody(String recipient, String message) {
        return new SmsBody(recipient, message);
    }
}
