package pattern1_creation.create1_builder.code.example4_default_params;

import java.util.Objects;

/**
 * Конфигурация сервера — пример Builder с параметрами по умолчанию.
 * <p>
 * Проблема: Java не поддерживает default parameters как, например, Kotlin:
 * fun startServer(port: Int = 8080, host: String = "0.0.0.0") — так нельзя.
 * <p>
 * Решение через Builder: все умолчания задаются в Builder-е. Минимальный вызов:
 * new ServerConfig.Builder("myapp").build()
 * — и сервер готов к запуску с разумными настройками.
 * <p>
 * 1 обязательный параметр + 13 опциональных с промышленными умолчаниями.
 */
public class ServerConfig {

    // Обязательный параметр — имя приложения
    private final String applicationName;

    // ─── Сеть ────────────────────────────────────────────────────────────────
    private final String host;              // хост для привязки (0.0.0.0 = все интерфейсы)
    private final int port;                 // основной HTTP-порт
    private final boolean sslEnabled;       // включить HTTPS
    private final int sslPort;              // порт для HTTPS

    // ─── Производительность ──────────────────────────────────────────────────
    private final int maxConnections;       // максимальное количество одновременных соединений
    private final int connectionTimeout;    // таймаут установки соединения в секундах
    private final int readTimeout;          // таймаут чтения данных в секундах
    private final int threadPoolSize;       // размер пула потоков обработки запросов

    // ─── Кэш ─────────────────────────────────────────────────────────────────
    private final boolean cacheEnabled;     // включить кэширование
    private final int cacheMaxSizeMb;       // максимальный размер кэша в МБ
    private final int cacheTtlSeconds;      // время жизни записи в кэше (TTL)

    // ─── Логирование ─────────────────────────────────────────────────────────
    private final String logLevel;          // уровень логов: DEBUG, INFO, WARN, ERROR
    private final boolean logRequestsEnabled; // логировать ли каждый входящий запрос

    /**
     * Приватный конструктор — только через Builder.
     */
    private ServerConfig(Builder builder) {
        this.applicationName = builder.applicationName;
        this.host = builder.host;
        this.port = builder.port;
        this.sslEnabled = builder.sslEnabled;
        this.sslPort = builder.sslPort;
        this.maxConnections = builder.maxConnections;
        this.connectionTimeout = builder.connectionTimeout;
        this.readTimeout = builder.readTimeout;
        this.threadPoolSize = builder.threadPoolSize;
        this.cacheEnabled = builder.cacheEnabled;
        this.cacheMaxSizeMb = builder.cacheMaxSizeMb;
        this.cacheTtlSeconds = builder.cacheTtlSeconds;
        this.logLevel = builder.logLevel;
        this.logRequestsEnabled = builder.logRequestsEnabled;
    }

    // ─── Геттеры ──────────────────────────────────────────────────────────────

    public String getApplicationName() {
        return applicationName;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public int getSslPort() {
        return sslPort;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public int getCacheMaxSizeMb() {
        return cacheMaxSizeMb;
    }

    public int getCacheTtlSeconds() {
        return cacheTtlSeconds;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public boolean isLogRequestsEnabled() {
        return logRequestsEnabled;
    }

    // ─── Static Nested Builder ────────────────────────────────────────────────

    public static class Builder {

        // Единственный обязательный параметр
        private final String applicationName;

        // ─── Сеть — промышленные умолчания ───────────────────────────────────
        private String host = "0.0.0.0";         // слушаем все сетевые интерфейсы
        private int port = 8080;                 // стандартный HTTP-порт для приложений
        private boolean sslEnabled = false;      // SSL выключен — включается явно
        private int sslPort = 8443;              // стандартный HTTPS-порт для приложений

        // ─── Производительность — умолчания для небольшого сервиса ───────────
        private int maxConnections = 100;        // достаточно для dev/staging
        private int connectionTimeout = 10;      // 10 секунд — стандартный таймаут
        private int readTimeout = 30;            // 30 секунд — для обычных запросов
        private int threadPoolSize = 4;          // 4 потока — разумный старт

        // ─── Кэш — выключен по умолчанию (явное включение лучше сюрпризов) ──
        private boolean cacheEnabled = false;
        private int cacheMaxSizeMb = 256;        // 256 МБ — не слишком много
        private int cacheTtlSeconds = 3600;      // 1 час — стандартный TTL

        // ─── Логирование ─────────────────────────────────────────────────────
        private String logLevel = "INFO";               // INFO — баланс между деталями и шумом
        private boolean logRequestsEnabled = false;     // логирование запросов выключено (производительность)

        /**
         * Конструктор принимает только applicationName — единственный обязательный параметр.
         * Этим и демонстрируется главная идея примера: минимальный вызов build() работает.
         *
         * @param applicationName имя приложения (используется в логах, метриках и заголовках)
         */
        public Builder(String applicationName) {
            if (applicationName == null || applicationName.isBlank()) {
                throw new IllegalArgumentException("Имя приложения не может быть пустым");
            }
            this.applicationName = applicationName;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            if (port < 1 || port > 65535) {
                throw new IllegalArgumentException("Порт должен быть в диапазоне 1–65535: " + port);
            }
            this.port = port;
            return this;
        }

        /**
         * Включает SSL.
         * Метод без параметра: enableSsl() читается лучше, чем ssl(true).
         * Если нужен кастомный порт — следует вызвать sslPort() отдельно.
         */
        public Builder enableSsl() {
            this.sslEnabled = true;
            return this;
        }

        public Builder sslPort(int sslPort) {
            this.sslPort = sslPort;
            return this;
        }

        public Builder maxConnections(int maxConnections) {
            this.maxConnections = maxConnections;
            return this;
        }

        public Builder connectionTimeout(int seconds) {
            this.connectionTimeout = seconds;
            return this;
        }

        public Builder readTimeout(int seconds) {
            this.readTimeout = seconds;
            return this;
        }

        public Builder threadPoolSize(int size) {
            this.threadPoolSize = size;
            return this;
        }

        /**
         * Включает кэширование.
         * Аналогично enableSsl(): булев флаг лучше включать именованным методом.
         */
        public Builder enableCache() {
            this.cacheEnabled = true;
            return this;
        }

        public Builder cacheMaxSizeMb(int mb) {
            this.cacheMaxSizeMb = mb;
            return this;
        }

        public Builder cacheTtlSeconds(int seconds) {
            this.cacheTtlSeconds = seconds;
            return this;
        }

        public Builder logLevel(String level) {
            this.logLevel = level;
            return this;
        }

        /**
         * Включает логирование входящих запросов.
         * Выключено по умолчанию: каждый запрос в лог — это нагрузка на I/O.
         * Полезно для отладки, но не в production с высоким трафиком.
         */
        public Builder enableRequestLogging() {
            this.logRequestsEnabled = true;
            return this;
        }

        /**
         * Создаёт объект ServerConfig.
         *
         * @return готовая конфигурация сервера
         */
        public ServerConfig build() {
            return new ServerConfig(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerConfig that = (ServerConfig) o;
        return port == that.port &&
                sslEnabled == that.sslEnabled &&
                sslPort == that.sslPort &&
                maxConnections == that.maxConnections &&
                connectionTimeout == that.connectionTimeout &&
                readTimeout == that.readTimeout &&
                threadPoolSize == that.threadPoolSize &&
                cacheEnabled == that.cacheEnabled &&
                cacheMaxSizeMb == that.cacheMaxSizeMb &&
                cacheTtlSeconds == that.cacheTtlSeconds &&
                logRequestsEnabled == that.logRequestsEnabled &&
                Objects.equals(applicationName, that.applicationName) &&
                Objects.equals(host, that.host) &&
                Objects.equals(logLevel, that.logLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationName, host, port, sslEnabled, sslPort,
                maxConnections, connectionTimeout, readTimeout, threadPoolSize,
                cacheEnabled, cacheMaxSizeMb, cacheTtlSeconds, logLevel, logRequestsEnabled);
    }

    @Override
    public String toString() {
        return "ServerConfig{" +
                "app='" + applicationName + '\'' +
                ", " + host + ":" + port +
                (sslEnabled ? " (SSL:" + sslPort + ")" : "") +
                ", maxConn=" + maxConnections +
                ", timeout=" + connectionTimeout + "/" + readTimeout + "s" +
                ", threads=" + threadPoolSize +
                ", cache=" + (cacheEnabled ? cacheMaxSizeMb + "MB/TTL=" + cacheTtlSeconds + "s" : "off") +
                ", log=" + logLevel +
                (logRequestsEnabled ? "+requests" : "") +
                '}';
    }
}
