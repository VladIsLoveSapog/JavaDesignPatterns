package pattern1_creation.create3_abstract_factory.code.example2_notifications;

/**
 * [АНТИПАТТЕРН — до рефакторинга]
 * Сервис уведомлений без паттерна Абстрактная Фабрика.
 *
 * <p>Проблемы этого подхода:
 * <ul>
 *   <li>Добавить новый тип (Push, Telegram) = менять этот класс везде</li>
 *   <li>Ничто не мешает случайно смешать форматы (email-заголовок + sms-тело)</li>
 *   <li>Нарушение SRP: класс одновременно знает форматы email и sms</li>
 * </ul>
 */
public class BadNotificationService {

    public void send(String type, String recipient, String message) {
        if (type.equals("email")) {
            String header = "To: " + recipient + " | Subject: Notification";
            String body = "Dear " + recipient + ",\n" + message + "\n\nBest regards,\nSystem";
            System.out.println("[BAD EMAIL] Header: " + header);
            System.out.println("[BAD EMAIL] Body:   " + body);
        } else if (type.equals("sms")) {
            String header = "SMS to " + recipient;
            String body = "MSG: " + message;
            System.out.println("[BAD SMS] Header: " + header);
            System.out.println("[BAD SMS] Body:   " + body);
        } else {
            System.out.println("[BAD] Неизвестный тип уведомления: " + type);
        }
    }
}
