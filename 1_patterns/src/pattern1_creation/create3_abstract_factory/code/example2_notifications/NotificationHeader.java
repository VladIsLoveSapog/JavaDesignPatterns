package pattern1_creation.create3_abstract_factory.code.example2_notifications;

/**
 * Абстрактный продукт — заголовок уведомления.
 *
 * <p>Определяет общий контракт для заголовков уведомлений. Каждый канал
 * (Email, SMS) имеет свой формат заголовка, но клиентский код работает
 * только с этим интерфейсом, не завися от конкретных реализаций.</p>
 *
 * @see AbstractNotificationFactory
 */
public interface NotificationHeader {

    /**
     * Форматирует заголовок уведомления в строку, готовую к выводу.
     *
     * @return отформатированный заголовок
     */
    String format();
}
