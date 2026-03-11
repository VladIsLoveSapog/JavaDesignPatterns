package pattern1_creation.create1_builder.code.example3_interface_builder;

/**
 * Интерфейс Builder для создания уведомлений.
 * <p>
 * Зачем нужен интерфейс Builder?
 * ───────────────────────────────
 * Полиморфизм: Director (NotificationDirector) работает только с этим интерфейсом.
 * Один метод Director может отправить уведомление через Email, SMS или Push —
 * без знания о конкретной реализации Builder. Нужно сменить канал? Просто передай
 * другой Builder — код Director не меняется.
 * <p>
 * Все методы возвращают NotificationBuilder (не конкретный тип) —
 * это позволяет работать с цепочкой вызовов через интерфейс.
 */
public interface NotificationBuilder {

    /**
     * Устанавливает получателя.
     * Email: ivan@example.com
     * SMS: +79001234567
     * Push: device_token_abc123...
     */
    NotificationBuilder recipient(String recipient);

    /**
     * Тема уведомления (для SMS игнорируется или вставляется в начало тела).
     */
    NotificationBuilder subject(String subject);

    /**
     * Текст уведомления. Каждая реализация накладывает свои ограничения на длину.
     */
    NotificationBuilder body(String body);

    /**
     * Приоритет: LOW, NORMAL, HIGH, URGENT.
     */
    NotificationBuilder priority(String priority);

    /**
     * Требует ли уведомление подтверждения прочтения от получателя.
     */
    NotificationBuilder requiresAck(boolean requiresAck);

    /**
     * Создаёт объект Notification.
     * Каждая реализация проводит собственную валидацию перед созданием.
     *
     * @return готовое уведомление
     */
    Notification build();
}
