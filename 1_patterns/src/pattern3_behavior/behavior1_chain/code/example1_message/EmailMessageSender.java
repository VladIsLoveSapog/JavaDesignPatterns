package pattern3_behavior.behavior1_chain.code.example1_message;

/**
 * Конкретный обработчик — отправка уведомления по электронной почте.
 *
 * <p>Наследует {@link MessageSender} и реализует метод {@link #write(String)},
 * выводя сообщение в консоль с пометкой об отправке email.
 *
 * <p><b>Роль в паттерне:</b> конкретный обработчик (Concrete Handler),
 * который срабатывает при среднем или более высоком уровне приоритета
 * ({@link PriorityLevel#MIDDLE} и выше). Если в цепочке задан следующий
 * обработчик, запрос будет передан ему после выполнения отправки.
 *
 * @see MessageSender
 * @see PriorityLevel
 */
public class EmailMessageSender extends MessageSender {

    /**
     * Создаёт обработчик для отправки email с заданным уровнем приоритета.
     *
     * @param priorityLevel минимальный уровень приоритета, при котором
     *                      обработчик выполнит отправку email
     */
    public EmailMessageSender(PriorityLevel priorityLevel) {
        super(priorityLevel);
    }

    /**
     * Отправляет уведомление по электронной почте.
     *
     * <p>В данной реализации сообщение выводится в стандартный поток вывода.
     *
     * @param message текст сообщения для отправки
     */
    @Override
    protected void write(String message) {
        System.out.println("Sending email: " + message);
    }
}
