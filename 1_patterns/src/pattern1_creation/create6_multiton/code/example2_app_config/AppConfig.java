package pattern1_creation.create6_multiton.code.example2_app_config;

/**
 * Конфигурация приложения для конкретного окружения.
 *
 * <p>Хранит параметры подключения к базе данных и настройки логирования.
 * Все поля неизменяемы ({@code final}) — после создания экземпляра конфигурация не может быть изменена.</p>
 *
 * <h3>Контроль доступа</h3>
 * <p>Класс и конструктор имеют <b>package-private</b> модификатор доступа —
 * создать экземпляр {@code AppConfig} извне пакета невозможно. Единственный способ получить конфигурацию — через
 * мультитон {@link AppConfigMultiton#getConfig(Environment)}.</p>
 *
 * <p>Геттеры при этом {@code public} — клиентский код может читать
 * параметры конфигурации, но не может создавать или модифицировать экземпляры.</p>
 */
class AppConfig {
    private final String databaseUrl;
    private final String databaseName;
    private final String logLevel;
    private final boolean debugMode;

    /**
     * Создаёт конфигурацию с указанными параметрами.
     *
     * @param databaseUrl  URL подключения к базе данных (например, {@code jdbc:postgresql://localhost:5432})
     * @param databaseName имя базы данных
     * @param logLevel     уровень логирования ({@code DEBUG}, {@code INFO}, {@code WARN} и т.д.)
     * @param debugMode    {@code true}, если включён режим отладки
     */
    AppConfig(String databaseUrl, String databaseName, String logLevel, boolean debugMode) {
        this.databaseUrl = databaseUrl;
        this.databaseName = databaseName;
        this.logLevel = logLevel;
        this.debugMode = debugMode;
    }

    /**
     * Возвращает URL подключения к базе данных.
     *
     * @return URL базы данных
     */
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    /**
     * Возвращает имя базы данных.
     *
     * @return имя базы данных
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Возвращает уровень логирования.
     *
     * @return уровень логирования (например, {@code DEBUG}, {@code INFO}, {@code WARN})
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * Проверяет, включён ли режим отладки.
     *
     * @return {@code true}, если режим отладки включён
     */
    public boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Возвращает строковое представление конфигурации со всеми параметрами.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "AppConfig{"
                + "databaseUrl='" + databaseUrl + '\''
                + ", databaseName='" + databaseName + '\''
                + ", logLevel='" + logLevel + '\''
                + ", debugMode=" + debugMode
                + '}';
    }
}
