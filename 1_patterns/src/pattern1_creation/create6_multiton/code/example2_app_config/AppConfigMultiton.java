package pattern1_creation.create6_multiton.code.example2_app_config;

import java.util.EnumMap;
import java.util.Map;

/**
 * Мультитон конфигураций приложения по окружениям.
 *
 * <p>Управляет фиксированным набором экземпляров {@link AppConfig},
 * по одному на каждый элемент {@link Environment}. Использует {@link EnumMap} для типобезопасного и эффективного
 * хранения.</p>
 *
 * <h3>Жадная инициализация</h3>
 * <p>Все конфигурации создаются в статическом блоке инициализации при загрузке
 * класса. Это обеспечивает потокобезопасность без дополнительной синхронизации — JVM гарантирует, что статический блок
 * выполняется ровно один раз.</p>
 *
 * <h3>Контроль доступа</h3>
 * <p>Класс {@link AppConfig} имеет package-private модификатор доступа —
 * создать конфигурацию извне пакета невозможно. Единственный способ получить конфигурацию — через метод
 * {@link #getConfig(Environment)}.</p>
 *
 * <h3>Пример использования</h3>
 * <pre>{@code
 * AppConfig config = AppConfigMultiton.getConfig(Environment.PROD);
 * System.out.println(config.getDatabaseUrl());
 * }</pre>
 *
 * @see AppConfig
 * @see Environment
 */
public class AppConfigMultiton {

    /**
     * Хранилище конфигураций: одна {@link AppConfig} на каждый {@link Environment}.
     */
    private static final Map<Environment, AppConfig> configs = new EnumMap<>(Environment.class);

    static {
        configs.put(Environment.DEV, new AppConfig(
                "jdbc:postgresql://localhost:5432", "app_dev", "DEBUG", true));
        configs.put(Environment.STAGING, new AppConfig(
                "jdbc:postgresql://staging-db:5432", "app_staging", "INFO", false));
        configs.put(Environment.PROD, new AppConfig(
                "jdbc:postgresql://prod-db:5432", "app_prod", "WARN", false));
    }

    /**
     * Приватный конструктор для предотвращения создания экземпляров класса-мультитона.
     */
    private AppConfigMultiton() {
    }

    /**
     * Возвращает конфигурацию для указанного окружения.
     *
     * <p>При каждом вызове с одним и тем же ключом возвращается один
     * и тот же экземпляр {@link AppConfig} — это гарантия паттерна Мультитон.</p>
     *
     * @param environment окружение, для которого запрашивается конфигурация
     * @return экземпляр {@link AppConfig}, соответствующий указанному окружению
     */
    public static AppConfig getConfig(Environment environment) {
        return configs.get(environment);
    }
}
