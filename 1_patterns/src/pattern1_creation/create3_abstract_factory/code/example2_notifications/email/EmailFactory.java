package pattern1_creation.create3_abstract_factory.code.example2_notifications.email;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.AbstractNotificationFactory;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationBody;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationHeader;

/**
 * [ConcreteFactory]
 * Фабрика email-уведомлений.
 *
 * <p>Возвращает конкретные типы {@link EmailHeader} и {@link EmailBody},
 * но сигнатура методов использует абстрактные интерфейсы — клиент не зависит
 * от конкретных классов.
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
