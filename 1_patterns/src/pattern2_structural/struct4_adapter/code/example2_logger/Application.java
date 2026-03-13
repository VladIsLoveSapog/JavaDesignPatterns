package pattern2_structural.struct4_adapter.code.example2_logger;

/**
 * Демонстрация работы паттерна Адаптер на примере системы логирования.
 *
 * <p>Класс {@code Application} — это клиент, который использует интерфейс
 * {@link Logger} для записи логов. Он ничего не знает о {@link NewLogger}
 * и работает через адаптер {@link LoggerAdapter}.
 *
 * <p><b>Что демонстрирует этот пример:</b> мы подключили новую систему
 * логирования, не изменив ни одной строчки в классе {@code Application}.
 * Достаточно было создать адаптер и передать его в конструктор.
 */
public class Application {

    /** Логгер, с которым работает приложение (целевой интерфейс). */
    private final Logger logger;

    /**
     * Принимает логгер через конструктор — это может быть как прямая
     * реализация {@link Logger}, так и адаптер {@link LoggerAdapter}.
     * Клиенту всё равно — он работает с интерфейсом.
     *
     * @param logger реализация интерфейса Logger
     */
    public Application(Logger logger) {
        this.logger = logger;
    }

    /**
     * Имитирует работу приложения с логированием.
     * Демонстрирует запись информационных сообщений и ошибок.
     */
    public void doWork() {
        logger.logInfo("Application is starting.");
        try {
            // Имитация полезной работы приложения
            System.out.println("Application is working...");
            throw new RuntimeException("Something went wrong!");
        } catch (Exception e) {
            // Логируем ошибку через тот же интерфейс Logger
            logger.logError(e.getMessage());
        } finally {
            logger.logInfo("Application is shutting down.");
        }
    }

    /**
     * Точка входа — демонстрация подключения нового логгера через адаптер.
     */
    public static void main(String[] args) {
        System.out.println("=== Adapter: Подключение нового логгера через адаптер ===\n");

        // 1. Создаём экземпляр нового логгера (Adaptee)
        NewLogger newLogger = new NewLogger();

        // 2. Оборачиваем его в адаптер, который реализует интерфейс Logger
        Logger loggerAdapter = new LoggerAdapter(newLogger);

        // 3. Передаём адаптер в приложение — оно работает с интерфейсом Logger,
        //    не зная, что за ним стоит NewLogger
        Application app = new Application(loggerAdapter);
        app.doWork();
    }
}
