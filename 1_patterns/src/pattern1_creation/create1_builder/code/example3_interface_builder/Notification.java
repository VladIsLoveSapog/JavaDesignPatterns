package pattern1_creation.create1_builder.code.example3_interface_builder;

import java.util.Objects;

/**
 * Уведомление — продукт, который умеют создавать разные Builder-ы.
 *
 * Объект создаётся только через Builder-ы, реализующие NotificationBuilder.
 *
 * Все три канала (Email, SMS, Push) создают один и тот же тип объекта Notification,
 * но с разными данными и ограничениями (например, SMS не длиннее 160 символов).
 */
public class Notification {

    private final String type;        // тип канала: EMAIL, SMS, PUSH
    private final String recipient;   // получатель: адрес, номер телефона, device token
    private final String subject;     // тема уведомления
    private final String body;        // тело уведомления
    private final String priority;    // приоритет: LOW, NORMAL, HIGH, URGENT
    private final boolean requiresAck; // требует ли подтверждения прочтения

    public Notification(String type, String recipient, String subject,
                 String body, String priority, boolean requiresAck) {
        this.type = type;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.priority = priority;
        this.requiresAck = requiresAck;
    }

    // ─── Геттеры ──────────────────────────────────────────────────────────────

    public String getType() { return type; }
    public String getRecipient() { return recipient; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public String getPriority() { return priority; }
    public boolean isRequiresAck() { return requiresAck; }

    // ─── Object methods ───────────────────────────────────────────────────────

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return requiresAck == that.requiresAck &&
                Objects.equals(type, that.type) &&
                Objects.equals(recipient, that.recipient) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(body, that.body) &&
                Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, recipient, subject, body, priority, requiresAck);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "type=" + type +
                ", recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + (body.length() > 50 ? body.substring(0, 50) + "..." : body) + '\'' +
                ", priority=" + priority +
                ", requiresAck=" + requiresAck +
                '}';
    }
}
