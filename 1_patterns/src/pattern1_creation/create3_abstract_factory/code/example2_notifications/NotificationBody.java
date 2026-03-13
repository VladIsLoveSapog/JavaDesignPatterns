package pattern1_creation.create3_abstract_factory.code.example2_notifications;

/**
 * Абстрактный продукт — тело уведомления.
 *
 * <p>Определяет общий контракт для тела уведомлений. Каждый канал
 * (Email, SMS) имеет свой формат тела сообщения, но клиентский код работает
 * только с этим интерфейсом, не завися от конкретных реализаций.</p>
 *
 * @see AbstractNotificationFactory
 */
public interface NotificationBody {

    /**
     * Форматирует тело уведомления в строку, готовую к выводу.
     *
     * @return отформатированное тело
     */
    String format();
}
