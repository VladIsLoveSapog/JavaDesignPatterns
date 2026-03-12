package pattern1_creation.create3_abstract_factory.code.example2_notifications.sms;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.AbstractNotificationFactory;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationBody;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationHeader;

/**
 * [ConcreteFactory]
 * Фабрика SMS-уведомлений.
 *
 * <p>Возвращает конкретные типы {@link SmsHeader} и {@link SmsBody},
 * но сигнатура методов использует абстрактные интерфейсы — клиент не зависит
 * от конкретных классов.
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
