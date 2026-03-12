package pattern1_creation.create3_abstract_factory.code.example2_notifications.sms;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationHeader;

/**
 * [ConcreteProduct]
 * Заголовок SMS-уведомления.
 *
 * <p>Создаётся только через {@link SmsFactory} — не инстанциируй напрямую в клиентском коде.
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
