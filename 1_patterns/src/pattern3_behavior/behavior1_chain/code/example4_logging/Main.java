package pattern3_behavior.behavior1_chain.code.example4_logging;

/**
 * <p><b>Демонстрация паттерна «Цепочка обязанностей»</b> на примере
 * конвейера обработки логов.</p>
 *
 * <p>В данном примере цепочка <b>всегда проходится полностью</b> — каждый
 * обработчик выполняет свою задачу и безусловно передаёт запись дальше.
 * Это отличает данный вариант от классической реализации, где обработчик
 * может прервать цепочку, если запрос обработан.</p>
 *
 * <p>Создаёт конвейер обработчиков:
 * {@link TimestampProcessor} &rarr; {@link FormatProcessor} &rarr;
 * {@link FilterProcessor} &rarr; {@link ConsoleOutputProcessor}
 * и демонстрирует два сценария:</p>
 * <ul>
 *     <li><b>Тест 1:</b> запись с уровнем {@code "INFO"} — проходит через
 *         все 4 обработчика и выводится в консоль.</li>
 *     <li><b>Тест 2:</b> запись с уровнем {@code "DEBUG"} — также проходит
 *         через все 4 обработчика, но помечается для фильтрации,
 *         и вывод в консоль пропускается.</li>
 * </ul>
 *
 * @see LogProcessor
 * @see BaseLogProcessor
 * @see LogRecord
 */
public class Main {

    /**
     * Точка входа в программу.
     *
     * <p>Собирает конвейер обработки логов и демонстрирует прохождение
     * записей через полную цепочку обработчиков.</p>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создание обработчиков
        BaseLogProcessor timestampProcessor = new TimestampProcessor();
        BaseLogProcessor formatProcessor = new FormatProcessor();
        BaseLogProcessor filterProcessor = new FilterProcessor();
        BaseLogProcessor consoleOutputProcessor = new ConsoleOutputProcessor();

        // Настройка цепочки: Timestamp -> Format -> Filter -> ConsoleOutput
        timestampProcessor.setNext(formatProcessor);
        formatProcessor.setNext(filterProcessor);
        filterProcessor.setNext(consoleOutputProcessor);

        // Тест 1: Запись с уровнем INFO — проходит весь конвейер и выводится
        System.out.println("=== Тест 1: Запись INFO ===");
        LogRecord infoRecord = new LogRecord("INFO", "Приложение запущено");
        timestampProcessor.process(infoRecord);
        System.out.println("Итоговое состояние записи: " + infoRecord);

        System.out.println();
        System.out.println("-----");
        System.out.println();

        // Тест 2: Запись с уровнем DEBUG — проходит весь конвейер, но вывод пропущен
        System.out.println("=== Тест 2: Запись DEBUG ===");
        LogRecord debugRecord = new LogRecord("DEBUG", "Отладочная информация");
        timestampProcessor.process(debugRecord);
        System.out.println("Итоговое состояние записи: " + debugRecord);

        System.out.println();
        System.out.println("-----");
        System.out.println();

        // Вывод итогов: цепочка была полностью пройдена для обеих записей
        System.out.println("=== Результат ===");
        System.out.println("Запись INFO  -> timestamp: " + (infoRecord.getTimestamp() != null) +
                ", formatted: " + infoRecord.isFormatted() +
                ", filtered: " + infoRecord.isFiltered() +
                " => цепочка пройдена полностью");
        System.out.println("Запись DEBUG -> timestamp: " + (debugRecord.getTimestamp() != null) +
                ", formatted: " + debugRecord.isFormatted() +
                ", filtered: " + debugRecord.isFiltered() +
                " => цепочка пройдена полностью");
    }
}
