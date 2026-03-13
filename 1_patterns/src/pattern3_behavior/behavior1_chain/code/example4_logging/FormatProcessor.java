package pattern3_behavior.behavior1_chain.code.example4_logging;

/**
 * <p><b>Обработчик форматирования</b> — конкретное звено конвейера логирования.</p>
 *
 * <p>Отвечает за форматирование сообщения записи лога ({@link LogRecord}).
 * Преобразует исходное сообщение в стандартный формат вида
 * {@code "[LEVEL] timestamp - message"}, объединяя уровень логирования,
 * временную метку и текст сообщения.</p>
 *
 * <p>Для корректной работы рекомендуется размещать данный обработчик
 * <b>после</b> {@link TimestampProcessor}, чтобы временная метка
 * уже была установлена к моменту форматирования.</p>
 *
 * <p>После форматирования устанавливается флаг
 * {@link LogRecord#setFormatted(boolean) formatted} в значение {@code true},
 * а запись <b>безусловно</b> передаётся следующему обработчику
 * благодаря механизму, реализованному в {@link BaseLogProcessor}.</p>
 *
 * @see BaseLogProcessor
 * @see LogRecord
 * @see TimestampProcessor
 */
public class FormatProcessor extends BaseLogProcessor {

    /**
     * Форматирует сообщение записи лога.
     *
     * <p>Преобразует сообщение в формат {@code "[LEVEL] timestamp - message"},
     * устанавливает флаг {@link LogRecord#setFormatted(boolean) formatted}
     * в {@code true} и выводит информационное сообщение в консоль.</p>
     *
     * @param record запись лога, сообщение которой будет отформатировано
     */
    @Override
    protected void doProcess(LogRecord record) {
        record.setFormatted(true);
        String formattedMessage = "[" + record.getLevel() + "] " + record.getTimestamp() + " - " + record.getMessage();
        record.setMessage(formattedMessage);
        System.out.println("FormatProcessor: Сообщение отформатировано");
    }
}
