package pattern2_structural.struct6_proxy.code.example1_cache;

/**
 * Паттерн Заместитель (Proxy) — реальный сервис данных (Real Subject).
 * <p>
 * Выполняет фактическую загрузку данных из внешнего источника.
 * Операция загрузки является дорогостоящей (симулируется задержкой в 3 секунды),
 * поэтому целесообразно использовать кэширующий прокси {@link CachingDataServiceProxy}
 * для повторных запросов с одинаковыми параметрами.
 * <p>
 * Реализует интерфейс {@link DataService}, что позволяет прозрачно
 * подменять реальный сервис прокси-заместителем.
 */
public class DataServiceImpl implements DataService {

    /**
     * Загружает данные по указанному параметру из внешнего источника.
     * <p>
     * Перед возвратом результата выполняется дорогостоящая операция
     * (симуляция обращения к внешнему API).
     *
     * @param parameter ключ, по которому запрашиваются данные
     * @return строковое представление загруженных данных
     */
    @Override
    public String fetchData(String parameter) {
        // Симуляция дорогостоящей операции, например, запрос к внешнему API
        simulateExpensiveOperation();
        return "Data for " + parameter;
    }

    /**
     * Симулирует дорогостоящую операцию загрузки данных.
     * <p>
     * В реальном приложении здесь мог бы быть HTTP-запрос к внешнему API,
     * обращение к базе данных или другая ресурсоёмкая операция.
     */
    private void simulateExpensiveOperation() {
        try {
            System.out.println("Fetching data from external source...");
            Thread.sleep(3000); // Симуляция задержки 3 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
