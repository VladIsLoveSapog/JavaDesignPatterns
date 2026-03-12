package pattern1_creation.create3_abstract_factory.code.example2_notifications;

import pattern1_creation.create3_abstract_factory.code.example2_notifications.email.EmailFactory;
import pattern1_creation.create3_abstract_factory.code.example2_notifications.sms.SmsFactory;

/**
 * [Client]
 * Демонстрация паттерна Абстрактная Фабрика на примере уведомлений.
 *
 * <p>Сначала показывается антипаттерн (if-else в {@link BadNotificationService}),
 * затем — чистый вариант через фабрики.
 *
 * <p>Переменная типа {@link AbstractNotificationFactory} позволяет поменять
 * весь канал уведомлений, изменив одну строку.
 */
public class NotificationMain {

    /**
     * Отправляет уведомление через переданную фабрику.
     * Клиентский код не знает, email это или sms.
     */
    private static void sendNotification(AbstractNotificationFactory factory,
                                         String recipient, String message) {
        NotificationHeader header = factory.createHeader(recipient);
        NotificationBody body = factory.createBody(recipient, message);
        System.out.println("  Header: " + header.format());
        System.out.println("  Body:   " + body.format());
    }

    public static void main(String[] args) {
        // ── АНТИПАТТЕРН: if-else ──────────────────────────────────────────
        System.out.println("=== АНТИПАТТЕРН (if-else) ===");
        BadNotificationService bad = new BadNotificationService();
        bad.send("email", "user@example.com", "Ваш заказ подтверждён");
        bad.send("sms",   "+79001234567",      "Заказ подтверждён");
        System.out.println();

        // ── ПАТТЕРН: Абстрактная Фабрика ─────────────────────────────────
        System.out.println("=== ПАТТЕРН: Абстрактная Фабрика ===");

        System.out.println("--- Email ---");
        AbstractNotificationFactory factory = new EmailFactory();
        sendNotification(factory, "user@example.com", "Ваш заказ подтверждён");
        System.out.println();

        System.out.println("--- SMS ---");
        factory = new SmsFactory();   // меняем канал — одна строка
        sendNotification(factory, "+79001234567", "Заказ подтверждён");
    }
}
