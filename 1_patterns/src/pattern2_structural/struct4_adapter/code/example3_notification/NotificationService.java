package pattern2_structural.struct4_adapter.code.example3_notification;

/**
 * Целевой интерфейс (Target) — сервис уведомлений в нашей системе.
 *
 * <p>Вся наша система отправляет уведомления пользователям через этот интерфейс.
 * Он оперирует понятиями нашей предметной области: имя пользователя, заголовок,
 * текст сообщения.
 *
 * <p>Мы хотим добавить отправку уведомлений через Telegram, но Telegram Bot API
 * ({@link TelegramBotApi}) имеет совершенно другой интерфейс: он работает
 * с числовыми идентификаторами чатов и единым текстовым сообщением.
 * Переписывать всю систему под Telegram — не вариант. Решение — адаптер.
 */
public interface NotificationService {

    /**
     * Отправить уведомление пользователю.
     *
     * @param recipientName имя получателя (например, "ivanov_ivan")
     * @param title         заголовок уведомления
     * @param body          текст уведомления
     */
    void sendNotification(String recipientName, String title, String body);
}
