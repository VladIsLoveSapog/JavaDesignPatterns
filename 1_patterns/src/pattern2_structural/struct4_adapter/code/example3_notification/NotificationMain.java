package pattern2_structural.struct4_adapter.code.example3_notification;

/**
 * Демонстрация работы паттерна Адаптер на примере системы уведомлений.
 *
 * <p>Клиентский код отправляет уведомления через интерфейс {@link NotificationService}.
 * Благодаря адаптеру {@link TelegramNotificationAdapter} уведомления уходят
 * в Telegram, хотя клиент ничего не знает о Telegram Bot API.
 */
public class NotificationMain {

    public static void main(String[] args) {
        System.out.println("=== Adapter: Отправка уведомлений через Telegram ===\n");

        // 1. Создаём сторонний сервис (Adaptee)
        TelegramBotApi telegramBot = new TelegramBotApi();

        // 2. Оборачиваем его в адаптер, реализующий наш интерфейс
        NotificationService notifier = new TelegramNotificationAdapter(telegramBot);

        // 3. Клиентский код работает с интерфейсом NotificationService,
        //    не подозревая о Telegram внутри
        notifier.sendNotification(
                "ivanov_ivan",
                "Оценка выставлена",
                "Вам поставлена оценка 5 по предмету 'Паттерны проектирования'."
        );

        System.out.println();

        notifier.sendNotification(
                "petrova_anna",
                "Новое задание",
                "Доступна лабораторная работа #3. Срок сдачи: 25.03.2026."
        );

        System.out.println();

        // Попытка отправить уведомление несуществующему пользователю
        notifier.sendNotification(
                "unknown_user",
                "Тест",
                "Это сообщение не будет доставлено."
        );
    }
}
