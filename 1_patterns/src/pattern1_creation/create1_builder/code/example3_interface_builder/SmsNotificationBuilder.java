package pattern1_creation.create1_builder.code.example3_interface_builder;

/**
 * Builder для SMS-уведомлений.
 * <p>
 * Специфика SMS:
 * - Ограничение: 160 символов (стандарт GSM 7-bit). Если тело длиннее — обрезаем.
 * - Получатель — номер телефона, должен начинаться с "+".
 * - Subject игнорируется (SMS не имеет темы) — но мы сохраним его в поле Notification
 * для совместимости интерфейса.
 * - Важно предупредить разработчика об обрезке текста.
 */
public class SmsNotificationBuilder implements NotificationBuilder {

    private static final int MAX_SMS_LENGTH = 160; // стандарт GSM

    private String recipient = "";
    private String subject = "";
    private String body = "";
    private String priority = "NORMAL";
    private boolean requiresAck = false;

    // Флаг: было ли тело обрезано до 160 символов
    private boolean bodyWasTruncated = false;

    @Override
    public NotificationBuilder recipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    @Override
    public NotificationBuilder subject(String subject) {
        // Subject в SMS не используется, но принимаем для совместимости интерфейса
        this.subject = subject;
        return this;
    }

    @Override
    public NotificationBuilder body(String body) {
        if (body != null && body.length() > MAX_SMS_LENGTH) {
            // Предупреждаем немедленно при установке тела, а не при build()
            System.out.println("[SMS] ПРЕДУПРЕЖДЕНИЕ: текст обрезан до " +
                    MAX_SMS_LENGTH + " символов (было " + body.length() + ")");
            this.body = body.substring(0, MAX_SMS_LENGTH);
            this.bodyWasTruncated = true;
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
     * Создаёт SMS-уведомление.
     * Валидирует формат номера телефона: должен начинаться с "+".
     */
    @Override
    public Notification build() {
        if (!recipient.startsWith("+")) {
            throw new IllegalArgumentException(
                    "Номер телефона должен начинаться с '+': '" + recipient + "'");
        }
        return new Notification("SMS", recipient, subject, body, priority, requiresAck);
    }

    /**
     * Можно проверить, было ли тело обрезано
     */
    public boolean isBodyWasTruncated() {
        return bodyWasTruncated;
    }
}
