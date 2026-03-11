package pattern1_creation.create1_builder.code.example3_interface_builder;

/**
 * Builder для Push-уведомлений (мобильные/браузерные).
 * <p>
 * Специфика Push:
 * - Тело ограничено 256 символами — обрезаем с предупреждением.
 * - Получатель — device token, должен быть не менее 32 символов.
 * - Subject отображается как заголовок push-уведомления (важен!).
 * - requiresAck: в мобильных push обычно нет явного подтверждения,
 * но для аналитики можно отслеживать "открыто".
 */
public class PushNotificationBuilder implements NotificationBuilder {

    private static final int MAX_PUSH_BODY_LENGTH = 256; // ограничение большинства push-платформ
    private static final int MIN_TOKEN_LENGTH = 32;      // минимальная длина device token

    private String recipient = "";
    private String subject = "";
    private String body = "";
    private String priority = "NORMAL";
    private boolean requiresAck = false;

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
        if (body != null && body.length() > MAX_PUSH_BODY_LENGTH) {
            System.out.println("[PUSH] ПРЕДУПРЕЖДЕНИЕ: текст обрезан до " +
                    MAX_PUSH_BODY_LENGTH + " символов (было " + body.length() + ")");
            this.body = body.substring(0, MAX_PUSH_BODY_LENGTH);
        } else {
            this.body = body != null ? body : "";
        }
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
     * Создаёт Push-уведомление.
     * Валидирует длину device token.
     */
    @Override
    public Notification build() {
        if (recipient.length() < MIN_TOKEN_LENGTH) {
            throw new IllegalArgumentException(
                    "Device token слишком короткий (минимум " + MIN_TOKEN_LENGTH +
                            " символов): длина=" + recipient.length());
        }
        return new Notification("PUSH", recipient, subject, body, priority, requiresAck);
    }
}
