package pattern1_creation.create3_abstract_factory.code.example2_notifications.sms;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationBody;

/**
 * [ConcreteProduct]
 * Тело SMS-уведомления.
 *
 * <p>Создаётся только через {@link SmsFactory} — не инстанциируй напрямую в клиентском коде.
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
