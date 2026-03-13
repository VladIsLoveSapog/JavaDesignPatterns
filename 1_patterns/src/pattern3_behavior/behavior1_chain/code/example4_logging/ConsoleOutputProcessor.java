package pattern3_behavior.behavior1_chain.code.example4_logging;

/**
 * <p><b>Обработчик консольного вывода</b> — конкретное звено конвейера логирования.</p>
 *
 * <p>Отвечает за финальный вывод записи лога ({@link LogRecord}) в консоль.
 * Перед выводом проверяет флаг {@link LogRecord#isFiltered() filtered}:
 * если запись помечена для фильтрации (например, обработчиком
 * {@link FilterProcessor}), вывод пропускается.</p>
 *
 * <p>Данный обработчик, как правило, является <b>последним</b> звеном
 * в цепочке, завершая конвейер обработки. Тем не менее, если после него
 * установлен следующий обработчик, запись будет передана далее благодаря
 * механизму, реализованному в {@link BaseLogProcessor}.</p>
 *
 * @see BaseLogProcessor
 * @see LogRecord
 * @see FilterProcessor
 */
public class ConsoleOutputProcessor extends BaseLogProcessor {

    /**
     * Выводит запись лога в консоль или пропускает вывод, если запись отфильтрована.
     *
     * <p>Если флаг {@link LogRecord#isFiltered() filtered} установлен
     * в {@code true}, выводится сообщение о пропуске. В противном случае
     * выводится отформатированное сообщение записи.</p>
     *
     * @param record запись лога для вывода в консоль
     */
    @Override
    protected void doProcess(LogRecord record) {
        if (record.isFiltered()) {
            System.out.println("ConsoleOutputProcessor: Запись отфильтрована, вывод пропущен");
        } else {
            System.out.println("ConsoleOutputProcessor: Вывод: " + record.getMessage());
        }
    }
}
