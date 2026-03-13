package pattern1_creation.create3_abstract_factory.code.example2_notifications.sms;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationHeader;

/**
 * Конкретный продукт — заголовок SMS-уведомления.
 *
 * <p>Реализует интерфейс {@link NotificationHeader} и форматирует заголовок
 * в стиле SMS-сообщения. Экземпляры создаются через фабрику
 * {@link SmsFactory}, что гарантирует согласованность семейства продуктов.</p>
 */
public class SmsHeader implements NotificationHeader {

    private final String recipient;

    public SmsHeader(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String format() {
        return "SMS to " + recipient;
    }
}
