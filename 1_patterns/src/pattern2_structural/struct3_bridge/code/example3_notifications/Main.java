package pattern2_structural.struct3_bridge.code.example3_notifications;

/**
 * Демонстрация паттерна Bridge на примере системы уведомлений.
 *
 * <p><b>Проблема без Bridge:</b> если у нас 2 формата сообщений (Text, HTML) и
 * 3 канала доставки (Email, SMS, Telegram), то без моста потребуется
 * 2 x 3 = 6 классов: {@code TextEmail}, {@code TextSms}, {@code TextTelegram},
 * {@code HtmlEmail}, {@code HtmlSms}, {@code HtmlTelegram}.
 * Добавление одного формата или одного канала приводит к комбинаторному взрыву.
 *
 * <p><b>Решение с Bridge:</b> формат (Abstraction) и канал (Implementation)
 * разделены мостом. Итого: 1 интерфейс {@link MessageSender} +
 * 3 конкретных канала + 1 абстрактный {@link Message} + 2 формата = 7 классов.
 * Добавление нового формата — один класс. Нового канала — тоже один класс.
 *
 * <p><b>Бонус:</b> канал доставки можно менять во время выполнения программы
 * (runtime), вызвав {@link Message#setSender(MessageSender)}, без пересоздания
 * объекта сообщения.
 */
public class Main {

    public static void main(String[] args) {

        // --- Каналы доставки (Implementation) ---
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender telegram = new TelegramSender();

        // ============================================================
        // 1. TextMessage через EmailSender
        // ============================================================
        System.out.println("=== TextMessage + EmailSender ===");
        Message textEmail = new TextMessage(email, "Добро пожаловать!", "Ваш аккаунт успешно создан.");
        textEmail.send("user@example.com");

        // ============================================================
        // 2. TextMessage через SmsSender
        // ============================================================
        System.out.println("\n=== TextMessage + SmsSender ===");
        Message textSms = new TextMessage(sms, "Код подтверждения", "Ваш код: 4829");
        textSms.send("+7-999-123-45-67");

        // ============================================================
        // 3. HtmlMessage через TelegramSender
        // ============================================================
        System.out.println("\n=== HtmlMessage + TelegramSender ===");
        Message htmlTelegram = new HtmlMessage(telegram, "Новое задание", "Решите задачу по паттернам проектирования.");
        htmlTelegram.send("@student");

        // ============================================================
        // 4. Смена канала доставки в runtime
        // ============================================================
        System.out.println("\n=== Смена канала: HtmlMessage Email -> SMS ===");
        Message htmlEmail = new HtmlMessage(email, "Уведомление", "Ваш заказ отправлен.");
        htmlEmail.send("user@example.com");

        System.out.println("--- Меняем канал на SMS ---");
        htmlEmail.setSender(sms);
        htmlEmail.send("+7-999-123-45-67");
    }
}
