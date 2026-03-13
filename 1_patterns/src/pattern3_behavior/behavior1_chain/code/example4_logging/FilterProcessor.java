package pattern3_behavior.behavior1_chain.code.example4_logging;

/**
 * <p><b>Обработчик фильтрации</b> — конкретное звено конвейера логирования.</p>
 *
 * <p>Отвечает за пометку записей лога ({@link LogRecord}), которые
 * должны быть отфильтрованы. Если уровень логирования записи —
 * {@code "DEBUG"}, обработчик устанавливает флаг
 * {@link LogRecord#setFiltered(boolean) filtered} в значение {@code true}.</p>
 *
 * <p><b>Важно:</b> данный обработчик <b>не прерывает</b> цепочку обработки.
 * Он лишь помечает запись, оставляя решение о действии (например,
 * пропуск вывода) последующим обработчикам, таким как
 * {@link ConsoleOutputProcessor}. Это ключевое отличие от классической
 * реализации, где обработчик может остановить дальнейшую обработку.</p>
 *
 * <p>После обработки запись <b>безусловно</b> передаётся следующему
 * обработчику благодаря механизму, реализованному в {@link BaseLogProcessor}.</p>
 *
 * @see BaseLogProcessor
 * @see LogRecord
 * @see ConsoleOutputProcessor
 */
public class FilterProcessor extends BaseLogProcessor {

    /**
     * Проверяет уровень логирования и помечает запись для фильтрации при необходимости.
     *
     * <p>Если уровень записи — {@code "DEBUG"}, устанавливает флаг
     * {@link LogRecord#setFiltered(boolean) filtered} в {@code true}
     * и выводит сообщение о пометке. В противном случае выводит
     * сообщение о том, что запись прошла фильтрацию.</p>
     *
     * @param record запись лога для проверки и возможной пометки
     */
    @Override
    protected void doProcess(LogRecord record) {
        if ("DEBUG".equalsIgnoreCase(record.getLevel())) {
            record.setFiltered(true);
            System.out.println("FilterProcessor: Запись помечена для фильтрации");
        } else {
            System.out.println("FilterProcessor: Запись прошла фильтрацию");
        }
    }
}
