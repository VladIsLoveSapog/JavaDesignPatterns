package pattern1_creation.create3_abstract_factory.code.example2_notifications;

/**
 * [AbstractProduct 2]
 * Абстрактный продукт — тело уведомления.
 *
 * <p>Каждый канал (Email, SMS) имеет свой формат тела сообщения,
 * но клиентский код работает только с этим интерфейсом.
 */
public interface NotificationBody {

    /**
     * Форматирует тело уведомления в строку, готовую к выводу.
     *
     * @return отформатированное тело
     */
    String format();
}
