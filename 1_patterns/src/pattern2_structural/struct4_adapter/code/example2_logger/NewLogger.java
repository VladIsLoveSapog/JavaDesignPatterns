package pattern2_structural.struct4_adapter.code.example2_logger;

/**
 * Адаптируемый класс (Adaptee) — новая система логирования с другим интерфейсом.
 *
 * <p>Этот логгер использует единый метод {@link #log(String, String)},
 * где уровень важности передаётся строковым параметром. Это распространённый
 * подход в современных библиотеках логирования (SLF4J, Log4j2 и др.).
 *
 * <p>Проблема: наша система использует интерфейс {@link Logger} с отдельными
 * методами для каждого уровня ({@code logInfo()}, {@code logError()}).
 * Интерфейсы несовместимы — нужен адаптер {@link LoggerAdapter}.
 */
public class NewLogger {

    /**
     * Записывает сообщение с указанным уровнем важности.
     *
     * @param severity уровень важности ("INFO", "ERROR", "WARN" и т.д.)
     * @param message  текст сообщения
     */
    public void log(String severity, String message) {
        System.out.println("[" + severity + "] " + message);
    }
}
