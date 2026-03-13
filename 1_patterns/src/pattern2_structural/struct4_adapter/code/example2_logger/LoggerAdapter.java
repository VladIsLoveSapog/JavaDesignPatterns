package pattern2_structural.struct4_adapter.code.example2_logger;

/**
 * Адаптер (Adapter) — приводит новый логгер к интерфейсу старого.
 *
 * <p>Реализует целевой интерфейс {@link Logger} (с отдельными методами
 * {@code logInfo()} и {@code logError()}), а внутри делегирует вызовы
 * новому логгеру {@link NewLogger} (с единым методом {@code log(severity, message)}).
 *
 * <p><b>Суть адаптации:</b> адаптер «переводит» вызовы из одного формата
 * в другой. Вызов {@code logInfo("текст")} превращается в
 * {@code newLogger.log("INFO", "текст")}.
 *
 * <p><b>Практический смысл:</b> мы подключили новую библиотеку логирования,
 * не изменив ни строчки в существующем клиентском коде, который работает
 * с интерфейсом {@code Logger}.
 */
public class LoggerAdapter implements Logger {

    /**
     * Ссылка на адаптируемый объект — новый логгер.
     * Передаётся через конструктор (Dependency Injection).
     */
    private final NewLogger newLogger;

    /**
     * Создаёт адаптер, оборачивающий новый логгер.
     *
     * @param newLogger экземпляр нового логгера, к которому будем адаптироваться
     */
    public LoggerAdapter(NewLogger newLogger) {
        this.newLogger = newLogger;
    }

    /**
     * Адаптирует вызов {@code logInfo()} к формату нового логгера.
     * Преобразование: {@code logInfo(msg)} → {@code log("INFO", msg)}.
     */
    @Override
    public void logInfo(String message) {
        newLogger.log("INFO", message);
    }

    /**
     * Адаптирует вызов {@code logError()} к формату нового логгера.
     * Преобразование: {@code logError(msg)} → {@code log("ERROR", msg)}.
     */
    @Override
    public void logError(String message) {
        newLogger.log("ERROR", message);
    }
}
