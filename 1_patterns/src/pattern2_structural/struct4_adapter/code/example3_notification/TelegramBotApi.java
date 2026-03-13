package pattern2_structural.struct4_adapter.code.example3_notification;

/**
 * Адаптируемый класс (Adaptee) — упрощённая имитация Telegram Bot API.
 *
 * <p>Представляет стороннюю библиотеку для отправки сообщений через Telegram.
 * Имеет собственный интерфейс, несовместимый с нашим {@link NotificationService}:
 * <ul>
 *   <li>Вместо имени пользователя принимает числовой {@code chatId}.</li>
 *   <li>Вместо раздельных заголовка и текста — одну строку {@code text}.</li>
 * </ul>
 *
 * <p>Мы не можем изменить этот класс — это внешняя библиотека.
 * Адаптер {@link TelegramNotificationAdapter} решит проблему несовместимости.
 */
public class TelegramBotApi {

    /**
     * Отправляет текстовое сообщение в указанный Telegram-чат.
     *
     * <p>В реальной библиотеке здесь был бы HTTP-запрос к Telegram API.
     * Для учебных целей просто выводим сообщение в консоль.
     *
     * @param chatId числовой идентификатор чата в Telegram
     * @param text   текст сообщения (одна строка, без разделения на заголовок и тело)
     */
    public void sendMessage(long chatId, String text) {
        System.out.println("[Telegram] Чат #" + chatId + ": " + text);
    }
}
