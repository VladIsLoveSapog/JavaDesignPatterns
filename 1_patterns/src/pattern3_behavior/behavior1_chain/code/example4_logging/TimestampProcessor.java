package pattern3_behavior.behavior1_chain.code.example4_logging;

import java.time.LocalDateTime;

/**
 * <p><b>Обработчик временной метки</b> — конкретное звено конвейера логирования.</p>
 *
 * <p>Отвечает за добавление временной метки к записи лога ({@link LogRecord}).
 * Использует {@link LocalDateTime#now()} для получения текущего времени
 * и устанавливает его в поле {@link LogRecord#setTimestamp(String) timestamp}
 * записи.</p>
 *
 * <p>Данный обработчик, как правило, является первым звеном в цепочке,
 * чтобы все последующие обработчики (например, {@link FormatProcessor})
 * могли использовать установленную временную метку.</p>
 *
 * <p>После обработки запись <b>безусловно</b> передаётся следующему
 * обработчику благодаря механизму, реализованному в {@link BaseLogProcessor}.</p>
 *
 * @see BaseLogProcessor
 * @see LogRecord
 * @see FormatProcessor
 */
public class TimestampProcessor extends BaseLogProcessor {

    /**
     * Добавляет временную метку к записи лога.
     *
     * <p>Устанавливает текущее время ({@link LocalDateTime#now()})
     * в поле {@link LogRecord#setTimestamp(String) timestamp} записи
     * и выводит информационное сообщение в консоль.</p>
     *
     * @param record запись лога, к которой будет добавлена временная метка
     */
    @Override
    protected void doProcess(LogRecord record) {
        record.setTimestamp(LocalDateTime.now().toString());
        System.out.println("TimestampProcessor: Добавлена временная метка");
    }
}
