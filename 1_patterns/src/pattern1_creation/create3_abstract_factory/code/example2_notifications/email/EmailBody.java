package pattern1_creation.create3_abstract_factory.code.example2_notifications.email;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationBody;

/**
 * [ConcreteProduct]
 * Тело email-уведомления.
 *
 * <p>Создаётся только через {@link EmailFactory} — не инстанциируй напрямую в клиентском коде.
 */
public class EmailBody implements NotificationBody {

    private final String recipient;
    private final String message;

    public EmailBody(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public String format() {
        return "Dear " + recipient + ",\n" + message + "\n\nBest regards,\nSystem";
    }
}
