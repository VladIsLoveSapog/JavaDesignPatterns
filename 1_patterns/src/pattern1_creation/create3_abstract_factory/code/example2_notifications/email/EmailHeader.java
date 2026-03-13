package pattern1_creation.create3_abstract_factory.code.example2_notifications.email;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationHeader;

/**
 * Конкретный продукт — заголовок email-уведомления.
 *
 * <p>Реализует интерфейс {@link NotificationHeader} и форматирует заголовок
 * в стиле электронной почты. Экземпляры создаются через фабрику
 * {@link EmailFactory}, что гарантирует согласованность семейства продуктов.</p>
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
