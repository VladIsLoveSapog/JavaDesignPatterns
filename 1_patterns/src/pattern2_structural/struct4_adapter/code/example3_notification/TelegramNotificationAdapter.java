package pattern2_structural.struct4_adapter.code.example3_notification;

import java.util.HashMap;
import java.util.Map;

/**
 * Адаптер (Adapter) — подключает Telegram Bot API к нашей системе уведомлений.
 *
 * <p>Реализует целевой интерфейс {@link NotificationService} (имя получателя,
 * заголовок, текст), а внутри делегирует отправку стороннему Telegram API
 * ({@link TelegramBotApi}), который работает с числовым chatId и единой строкой.
 *
 * <p><b>Суть адаптации — преобразование данных:</b>
 * <ol>
 *   <li>Имя пользователя ({@code String}) → числовой chatId ({@code long})
 *       через внутренний справочник пользователей.</li>
 *   <li>Заголовок + текст → единая отформатированная строка сообщения.</li>
 * </ol>
 *
 * <p><b>Ключевое отличие этого примера</b> от остальных: здесь адаптер не просто
 * делегирует вызов, а выполняет нетривиальное преобразование параметров.
 * В реальных проектах именно преобразование данных — основная задача адаптеров.
 */
public class TelegramNotificationAdapter implements NotificationService {

    /** Ссылка на адаптируемый объект — Telegram Bot API. */
    private final TelegramBotApi telegramBot;

    /**
     * Справочник: имя пользователя в нашей системе → chatId в Telegram.
     *
     * <p>В реальном приложении это была бы база данных или внешний сервис.
     * Для учебных целей используем простую {@code HashMap}.
     */
    private final Map<String, Long> userChatIds;

    /**
     * Создаёт адаптер с заданным Telegram Bot API.
     * Инициализирует справочник тестовыми данными для демонстрации.
     *
     * @param telegramBot экземпляр Telegram Bot API
     */
    public TelegramNotificationAdapter(TelegramBotApi telegramBot) {
        this.telegramBot = telegramBot;

        // Имитация базы данных с привязками пользователей к Telegram-чатам
        this.userChatIds = new HashMap<>();
        userChatIds.put("ivanov_ivan", 100001L);
        userChatIds.put("petrova_anna", 100002L);
        userChatIds.put("sidorov_petr", 100003L);
    }

    /**
     * Отправляет уведомление через Telegram, адаптируя параметры.
     *
     * <p>Преобразования:
     * <ul>
     *   <li>{@code recipientName} → {@code chatId} (через справочник)</li>
     *   <li>{@code title} + {@code body} → единая строка (формат Telegram)</li>
     * </ul>
     *
     * @param recipientName имя получателя в нашей системе
     * @param title         заголовок уведомления
     * @param body          текст уведомления
     */
    @Override
    public void sendNotification(String recipientName, String title, String body) {
        // Шаг 1: преобразуем имя пользователя в chatId Telegram
        Long chatId = userChatIds.get(recipientName);
        if (chatId == null) {
            System.out.println("[Adapter] Пользователь '" + recipientName
                    + "' не привязан к Telegram — уведомление не отправлено.");
            return;
        }

        // Шаг 2: объединяем заголовок и тело в одну строку (формат Telegram)
        String formattedMessage = "** " + title + " **\n" + body;

        // Шаг 3: делегируем отправку Telegram Bot API
        telegramBot.sendMessage(chatId, formattedMessage);
    }
}
