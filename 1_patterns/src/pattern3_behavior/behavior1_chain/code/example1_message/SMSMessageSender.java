package pattern3_behavior.behavior1_chain.code.example1_message;

/**
 * Конкретный обработчик — отправка уведомления по SMS.
 *
 * <p>Наследует {@link MessageSender} и реализует метод {@link #write(String)},
 * выводя сообщение в консоль с пометкой об отправке SMS менеджеру.
 *
 * <p><b>Роль в паттерне:</b> конкретный обработчик (Concrete Handler),
 * который обычно располагается в конце цепочки и срабатывает только при
 * высоком уровне приоритета ({@link PriorityLevel#HIGH}). Является последним
 * звеном эскалации уведомлений.
 *
 * @see MessageSender
 * @see PriorityLevel
 */
public class SMSMessageSender extends MessageSender {

    /**
     * Создаёт обработчик для отправки SMS с заданным уровнем приоритета.
     *
     * @param priorityLevel минимальный уровень приоритета, при котором
     *                      обработчик выполнит отправку SMS
     */
    public SMSMessageSender(PriorityLevel priorityLevel) {
        super(priorityLevel);
    }

    /**
     * Отправляет уведомление по SMS менеджеру.
     *
     * <p>В данной реализации сообщение выводится в стандартный поток вывода.
     *
     * @param message текст сообщения для отправки
     */
    @Override
    protected void write(String message) {
        System.out.println("Sending SMS to manager: " + message);
    }
}
