package pattern1_creation.create1_builder.code.example4_default_params;

/**
 * Демонстрация паттерна Builder как замены default parameters.
 *
 * Фокус: 1 обязательный параметр + 13 опциональных с умолчаниями.
 * Минимальный вызов: new ServerConfig.Builder("app").build()
 */
public class ServerConfigMain {
    public static void main(String[] args) {

        System.out.println("=== Пример 4: Builder с параметрами по умолчанию — ServerConfig ===\n");

        // ─── Сценарий 1: Минимальная конфигурация (1 параметр!) ───────────────
        System.out.println("── Сценарий 1: Минимальная конфигурация — только имя приложения ──");
        // Это главная идея примера: Builder с 13 дефолтами позволяет
        // создать полностью рабочую конфигурацию одной строчкой.
        ServerConfig minimal = new ServerConfig.Builder("hello-world-app")
                .build();
        System.out.println(minimal);
        System.out.println();

        // ─── Сценарий 2: Dev-конфигурация ────────────────────────────────────
        System.out.println("── Сценарий 2: Dev-конфигурация (отладка + логирование запросов) ──");
        ServerConfig dev = new ServerConfig.Builder("my-service-dev")
                .port(8081)
                .logLevel("DEBUG")
                .enableRequestLogging()  // логируем все запросы для отладки
                .threadPoolSize(2)       // в dev много потоков не нужно
                .build();
        System.out.println(dev);
        System.out.println();

        // ─── Сценарий 3: Production-конфигурация ──────────────────────────────
        System.out.println("── Сценарий 3: Production-конфигурация (SSL + кэш + высокая нагрузка) ──");
        ServerConfig prod = new ServerConfig.Builder("my-service-prod")
                .host("192.168.1.10")        // конкретный IP-адрес, не все интерфейсы
                .port(80)
                .enableSsl()                  // включаем HTTPS
                .sslPort(443)                 // стандартный HTTPS-порт
                .maxConnections(1000)         // высокая нагрузка
                .connectionTimeout(5)         // в prod не ждём долго установки соединения
                .readTimeout(15)
                .threadPoolSize(Runtime.getRuntime().availableProcessors() * 2)
                .enableCache()                // кэш в production критически важен
                .cacheMaxSizeMb(1024)         // 1 ГБ кэша
                .cacheTtlSeconds(300)         // 5 минут — частое обновление данных
                .logLevel("WARN")             // только предупреждения и ошибки
                .build();
        System.out.println(prod);
        System.out.println();

        // ─── Сценарий 4: Наглядное сравнение "без Builder vs с Builder" ───────
        System.out.println("── Сценарий 4: Сравнение подходов ──");
        System.out.println("БЕЗ Builder пришлось бы написать:");
        System.out.println("  new ServerConfig(\"analytics\", \"0.0.0.0\", 8080, false, 8443,");
        System.out.println("                   100, 10, 30, 4, false, 256, 3600, \"INFO\", false)");
        System.out.println("  → 14 параметров, непонятно что есть что без IDE подсказок");
        System.out.println();
        System.out.println("С Builder:");
        ServerConfig analytics = new ServerConfig.Builder("analytics")
                .port(9090)
                .enableCache()
                .logLevel("DEBUG")
                .build();
        System.out.println("  " + analytics);
        System.out.println("  → только нужные параметры, остальные — из умолчаний");
        System.out.println();

        // ─── Сценарий 5: Два микросервиса с общей базой ───────────────────────
        System.out.println("── Сценарий 5: Два микросервиса — общая prod-база настроек ──");
        // Одни и те же настройки производительности, разные имена и порты.
        // Builder позволяет легко "клонировать" конфигурацию с изменениями.
        int prodThreads = Runtime.getRuntime().availableProcessors() * 2;

        ServerConfig userService = new ServerConfig.Builder("user-service")
                .port(8001)
                .maxConnections(500)
                .threadPoolSize(prodThreads)
                .enableCache()
                .logLevel("INFO")
                .build();

        ServerConfig orderService = new ServerConfig.Builder("order-service")
                .port(8002)
                .maxConnections(500)    // те же настройки производительности
                .threadPoolSize(prodThreads)
                .enableCache()
                .cacheMaxSizeMb(512)    // но другой размер кэша
                .logLevel("INFO")
                .build();

        System.out.println("UserService:  " + userService);
        System.out.println("OrderService: " + orderService);
    }
}
