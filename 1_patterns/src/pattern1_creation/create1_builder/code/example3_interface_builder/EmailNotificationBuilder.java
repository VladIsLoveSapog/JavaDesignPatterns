package pattern1_creation.create1_builder.code.example3_interface_builder;

/**
 * Builder для email-уведомлений.
 * <p>
 * Специфика Email:
 * - Получатель должен содержать "@" — базовая валидация email-адреса.
 * - Нет ограничений на длину тела.
 * - Subject важен — отображается в почтовом клиенте до открытия письма.
 */
public class EmailNotificationBuilder implements NotificationBuilder {

    private String recipient = "";
    private String subject = "(без темы)";   // стандартное значение для писем без темы
    private String body = "";
    private String priority = "NORMAL";      // большинство писем — обычный приоритет
    private boolean requiresAck = false;     // по умолчанию подтверждение не требуется

    @Override
    public NotificationBuilder recipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    @Override
    public NotificationBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public NotificationBuilder body(String body) {
        this.body = body;
        return this;
    }

    @Override
    public NotificationBuilder priority(String priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public NotificationBuilder requiresAck(boolean requiresAck) {
        this.requiresAck = requiresAck;
        return this;
    }

    /**
     * Создаёт email-уведомление с валидацией адреса.
     * Проверяем наличие "@" — минимальная валидация, которая отсеивает явные ошибки.
     */
    @Override
    public Notification build() {
        if (!recipient.contains("@")) {
            throw new IllegalArgumentException(
                    "Некорректный email-адрес: '" + recipient + "' (отсутствует @)");
        }
        return new Notification("EMAIL", recipient, subject, body, priority, requiresAck);
    }
}
