package pattern1_creation.create3_abstract_factory.code.example2_notifications.email;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationHeader;

/**
 * [ConcreteProduct]
 * Заголовок email-уведомления.
 *
 * <p>Создаётся только через {@link EmailFactory} — не инстанциируй напрямую в клиентском коде.
 */
public class EmailHeader implements NotificationHeader {

    private final String recipient;

    public EmailHeader(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String format() {
        return "To: " + recipient + " | Subject: Notification";
    }
}
