package pattern1_creation.create3_abstract_factory.code.example2_notifications;

/**
 * [AbstractProduct 1]
 * Абстрактный продукт — заголовок уведомления.
 *
 * <p>Каждый канал (Email, SMS) имеет свой формат заголовка,
 * но клиентский код работает только с этим интерфейсом.
 */
public interface NotificationHeader {

    /**
     * Форматирует заголовок уведомления в строку, готовую к выводу.
     *
     * @return отформатированный заголовок
     */
    String format();
}
