package pattern3_behavior.behavior1_chain.code.example1_message;

/**
 * Конкретный обработчик — запись уведомления в лог-файл.
 *
 * <p>Наследует {@link MessageSender} и реализует метод {@link #write(String)},
 * выводя сообщение в консоль с пометкой о логировании.
 *
 * <p><b>Роль в паттерне:</b> конкретный обработчик (Concrete Handler),
 * который обычно располагается в начале цепочки с минимальным уровнем
 * приоритета ({@link PriorityLevel#LOW}). Срабатывает для всех сообщений
 * независимо от их приоритета, обеспечивая базовое логирование.
 *
 * @see MessageSender
 * @see PriorityLevel
 */
public class LogReportMessageSender extends MessageSender {

    /**
     * Создаёт обработчик для записи в лог с заданным уровнем приоритета.
     *
     * @param priorityLevel минимальный уровень приоритета, при котором
     *                      обработчик выполнит запись в лог
     */
    public LogReportMessageSender(PriorityLevel priorityLevel) {
        super(priorityLevel);
    }

    /**
     * Записывает уведомление в лог-отчёт.
     *
     * <p>В данной реализации сообщение выводится в стандартный поток вывода.
     *
     * @param message текст сообщения для записи в лог
     */
    @Override
    public void write(String message) {
        System.out.println("Message sender using simple log report: " + message);
    }
}
