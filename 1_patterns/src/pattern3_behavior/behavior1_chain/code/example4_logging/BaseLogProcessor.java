package pattern3_behavior.behavior1_chain.code.example4_logging;

/**
 * <p><b>Абстрактный обработчик (Base Handler)</b> в паттерне «Цепочка обязанностей».</p>
 *
 * <p>Предоставляет базовую реализацию интерфейса {@link LogProcessor},
 * инкапсулируя логику хранения ссылки на следующий обработчик и
 * безусловной передачи записи по цепочке.</p>
 *
 * <p>Ключевая особенность данной реализации — метод {@link #process(LogRecord)}
 * <b>всегда</b> передаёт запись следующему обработчику после вызова
 * {@link #doProcess(LogRecord)}, независимо от результата обработки.
 * Это гарантирует, что конвейер логирования проходится полностью
 * для каждой записи.</p>
 *
 * <p>Конкретные обработчики наследуют этот класс и реализуют метод
 * {@link #doProcess(LogRecord)}, определяя собственную логику обработки.</p>
 *
 * @see LogProcessor
 * @see TimestampProcessor
 * @see FormatProcessor
 * @see FilterProcessor
 * @see ConsoleOutputProcessor
 */
public abstract class BaseLogProcessor implements LogProcessor {

    /** Ссылка на следующий обработчик в цепочке. */
    protected LogProcessor nextProcessor;

    /**
     * {@inheritDoc}
     *
     * <p>Сохраняет ссылку на следующий обработчик, которому будет
     * безусловно передана запись лога после обработки текущим обработчиком.</p>
     *
     * @param next следующий обработчик в цепочке
     */
    @Override
    public void setNext(LogProcessor next) {
        this.nextProcessor = next;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Выполняет обработку записи, вызывая {@link #doProcess(LogRecord)},
     * а затем <b>безусловно</b> передаёт запись следующему обработчику
     * в цепочке (если он существует). Таким образом, цепочка обработки
     * никогда не прерывается — каждый обработчик гарантированно получит
     * запись.</p>
     *
     * @param record запись лога для обработки
     */
    @Override
    public void process(LogRecord record) {
        doProcess(record);
        if (nextProcessor != null) {
            nextProcessor.process(record);
        }
    }

    /**
     * Выполняет специфичную для конкретного обработчика логику обработки записи лога.
     *
     * <p>Данный метод реализуется каждым конкретным обработчиком в цепочке.
     * После его выполнения запись <b>всегда</b> передаётся следующему
     * обработчику — прерывание цепочки невозможно.</p>
     *
     * @param record запись лога для обработки
     */
    protected abstract void doProcess(LogRecord record);
}
