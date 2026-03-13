package pattern1_creation.create3_abstract_factory.code.example2_notifications.sms;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationBody;

/**
 * Конкретный продукт — тело SMS-уведомления.
 *
 * <p>Реализует интерфейс {@link NotificationBody} и форматирует тело сообщения
 * в стиле SMS. Экземпляры создаются через фабрику {@link SmsFactory},
 * что гарантирует согласованность семейства продуктов.</p>
 */
public class SmsBody implements NotificationBody {

    private final String recipient;
    private final String message;

    public SmsBody(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public String format() {
        return "MSG: " + message;
    }
}
