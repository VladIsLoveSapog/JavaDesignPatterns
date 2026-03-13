package pattern3_behavior.behavior1_chain.code.example1_message;

import java.util.Objects;

/**
 * Базовый абстрактный обработчик, который пересылает уведомления по цепочке.
 *
 * <p>Реализует интерфейс {@link Handler} и содержит общую логику маршрутизации
 * запросов: если уровень приоритета обработчика не превышает уровень сообщения,
 * обработчик выполняет действие через {@link #write(String)}, а затем передаёт
 * запрос следующему звену цепочки (если оно задано).
 *
 * <p><b>Роль в паттерне:</b> абстрактный обработчик (Base Handler) —
 * инкапсулирует логику передачи запроса по цепочке и хранит ссылку на
 * следующий обработчик. Конкретные обработчики ({@link EmailMessageSender},
 * {@link SMSMessageSender}, {@link LogReportMessageSender}) наследуют этот класс
 * и реализуют метод {@link #write(String)}.
 *
 * @see Handler
 * @see PriorityLevel
 */
public abstract class MessageSender implements Handler {

    /** Минимальный уровень приоритета, при котором этот обработчик срабатывает. */
    private final PriorityLevel priorityLevel;

    /** Следующий обработчик в цепочке (может быть {@code null}). */
    private Handler nextMessageHandler;

    /**
     * Создаёт обработчик с заданным уровнем приоритета.
     *
     * @param priorityLevel минимальный уровень приоритета, при котором
     *                      обработчик выполнит отправку сообщения
     */
    public MessageSender(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    /**
     * Устанавливает следующий обработчик в цепочке.
     *
     * @param handler следующий обработчик, которому будет передан запрос
     */
    @Override
    public void setNext(Handler handler) {
        this.nextMessageHandler = handler;
    }

    /**
     * Обрабатывает запрос: отправляет сообщение, если уровень приоритета
     * обработчика не превышает уровень сообщения, и передаёт запрос далее.
     *
     * <p>Если уровень этого обработчика ниже или равен уровню сообщения,
     * вызывается {@link #write(String)}. Затем, если задан следующий
     * обработчик, запрос передаётся ему.
     *
     * @param message текст сообщения для отправки
     * @param level   уровень приоритета сообщения
     */
    @Override
    public void handle(String message, PriorityLevel level) {
        // Если уровень этого обработчика ниже или равен уровню сообщения, то обрабатываем
        if (priorityLevel.ordinal() <= level.ordinal()) write(message);

        // В противном случае, если есть следующий обработчик, то передаём ему
        if (Objects.nonNull(nextMessageHandler)) nextMessageHandler.handle(message, level);
    }

    /**
     * Выполняет конкретное действие по отправке сообщения.
     *
     * <p>Метод реализуется в каждом конкретном обработчике и определяет
     * способ доставки уведомления (email, SMS, лог-файл и т.д.).
     *
     * @param message текст сообщения для отправки
     */
    protected abstract void write(String message);
}
