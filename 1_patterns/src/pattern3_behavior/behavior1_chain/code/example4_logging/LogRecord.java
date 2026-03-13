package pattern3_behavior.behavior1_chain.code.example4_logging;

/**
 * <p><b>Запрос (Request)</b> в паттерне «Цепочка обязанностей».</p>
 *
 * <p>Представляет запись лога, которая проходит через конвейер
 * обработчиков ({@link LogProcessor}). Каждый обработчик в цепочке
 * может обогащать, трансформировать или помечать запись, но цепочка
 * <b>всегда</b> проходится полностью — ни один обработчик не прерывает
 * её выполнение.</p>
 *
 * <p>Запись содержит следующие атрибуты:</p>
 * <ul>
 *     <li>{@code level} — уровень логирования (например, {@code "INFO"}, {@code "DEBUG"});</li>
 *     <li>{@code message} — текст сообщения;</li>
 *     <li>{@code timestamp} — временная метка, добавляемая {@link TimestampProcessor};</li>
 *     <li>{@code formatted} — флаг, указывающий, было ли сообщение
 *         отформатировано {@link FormatProcessor};</li>
 *     <li>{@code filtered} — флаг, указывающий, помечена ли запись
 *         для фильтрации {@link FilterProcessor}.</li>
 * </ul>
 *
 * @see LogProcessor
 * @see BaseLogProcessor
 */
public class LogRecord {

    /** Уровень логирования (например, {@code "INFO"}, {@code "DEBUG"}). */
    private String level;

    /** Текст сообщения лога. */
    private String message;

    /** Временная метка, добавляемая обработчиком {@link TimestampProcessor}. */
    private String timestamp;

    /** Признак того, что сообщение было отформатировано {@link FormatProcessor}. */
    private boolean formatted;

    /** Признак того, что запись помечена для фильтрации {@link FilterProcessor}. */
    private boolean filtered;

    /**
     * Создаёт новую запись лога с указанным уровнем и сообщением.
     *
     * <p>Поля {@code timestamp}, {@code formatted} и {@code filtered}
     * инициализируются значениями по умолчанию ({@code null}, {@code false},
     * {@code false} соответственно) и заполняются обработчиками
     * в процессе прохождения цепочки.</p>
     *
     * @param level   уровень логирования (например, {@code "INFO"}, {@code "DEBUG"})
     * @param message текст сообщения лога
     */
    public LogRecord(String level, String message) {
        this.level = level;
        this.message = message;
        this.timestamp = null;
        this.formatted = false;
        this.filtered = false;
    }

    /**
     * Возвращает уровень логирования записи.
     *
     * @return строковое представление уровня логирования
     */
    public String getLevel() {
        return level;
    }

    /**
     * Устанавливает уровень логирования записи.
     *
     * @param level новый уровень логирования
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Возвращает текст сообщения лога.
     *
     * @return текст сообщения
     */
    public String getMessage() {
        return message;
    }

    /**
     * Устанавливает текст сообщения лога.
     *
     * @param message новый текст сообщения
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Возвращает временную метку записи.
     *
     * @return строковое представление временной метки или {@code null},
     *         если метка ещё не была установлена
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Устанавливает временную метку записи.
     *
     * @param timestamp строковое представление временной метки
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Проверяет, было ли сообщение отформатировано.
     *
     * @return {@code true}, если сообщение прошло форматирование
     *         в {@link FormatProcessor}; {@code false} — в противном случае
     */
    public boolean isFormatted() {
        return formatted;
    }

    /**
     * Устанавливает признак форматирования сообщения.
     *
     * @param formatted {@code true}, если сообщение было отформатировано
     */
    public void setFormatted(boolean formatted) {
        this.formatted = formatted;
    }

    /**
     * Проверяет, помечена ли запись для фильтрации.
     *
     * @return {@code true}, если запись помечена для фильтрации
     *         в {@link FilterProcessor}; {@code false} — в противном случае
     */
    public boolean isFiltered() {
        return filtered;
    }

    /**
     * Устанавливает признак фильтрации записи.
     *
     * @param filtered {@code true}, если запись должна быть отфильтрована
     */
    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
    }

    /**
     * Возвращает строковое представление записи лога, включающее
     * все атрибуты: уровень, сообщение, временную метку, признаки
     * форматирования и фильтрации.
     *
     * @return строка вида {@code "LogRecord{level='INFO', message='...', timestamp='...', formatted=true, filtered=false}"}
     */
    @Override
    public String toString() {
        return "LogRecord{" +
                "level='" + level + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", formatted=" + formatted +
                ", filtered=" + filtered +
                '}';
    }
}
