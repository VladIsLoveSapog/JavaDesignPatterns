package pattern1_creation.create6_multiton.code.example2_app_config;

/**
 * Демонстрация использования паттерна Мультитон на примере конфигураций приложения.
 *
 * <p>Показывает основные сценарии работы с {@link AppConfigMultiton}:</p>
 * <ul>
 *   <li>Получение конфигураций для всех окружений ({@link Environment#DEV},
 *       {@link Environment#STAGING}, {@link Environment#PROD})</li>
 *   <li>Вывод параметров каждой конфигурации</li>
 *   <li>Проверка того, что повторное обращение к мультитону с тем же ключом
 *       возвращает тот же самый экземпляр {@link AppConfig} (оператор {@code ==})</li>
 * </ul>
 */
public class AppConfigMain {
    public static void main(String[] args) {
        // Получение конфигураций для разных окружений
        AppConfig devConfig = AppConfigMultiton.getConfig(Environment.DEV);
        AppConfig stagingConfig = AppConfigMultiton.getConfig(Environment.STAGING);
        AppConfig prodConfig = AppConfigMultiton.getConfig(Environment.PROD);

        System.out.println("DEV:     " + devConfig);
        System.out.println("STAGING: " + stagingConfig);
        System.out.println("PROD:    " + prodConfig);

        // Проверка единственности экземпляра для каждого окружения
        AppConfig anotherDevConfig = AppConfigMultiton.getConfig(Environment.DEV);
        System.out.println("\nDEV конфиги — один и тот же объект: "
                                   + (devConfig == anotherDevConfig));
    }
}
