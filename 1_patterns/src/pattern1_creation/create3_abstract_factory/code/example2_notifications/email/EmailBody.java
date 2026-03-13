package pattern1_creation.create3_abstract_factory.code.example2_notifications.email;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.NotificationBody;

/**
 * Конкретный продукт — тело email-уведомления.
 *
 * <p>Реализует интерфейс {@link NotificationBody} и форматирует тело сообщения
 * в стиле электронной почты. Экземпляры создаются через фабрику
 * {@link EmailFactory}, что гарантирует согласованность семейства продуктов.</p>
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
