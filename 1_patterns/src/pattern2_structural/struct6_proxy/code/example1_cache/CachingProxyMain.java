package pattern2_structural.struct6_proxy.code.example1_cache;

/**
 * Паттерн Заместитель (Proxy) — демонстрация кэширующего прокси.
 * <p>
 * Показывает, как {@link CachingDataServiceProxy} перехватывает вызовы
 * к реальному сервису {@link DataServiceImpl} и кэширует результаты.
 * При первом запросе данные загружаются из «внешнего источника» (с задержкой),
 * при повторном — мгновенно возвращаются из кэша.
 */
public class CachingProxyMain {
    public static void main(String[] args) {
        // Создаём реальный сервис, выполняющий дорогостоящую загрузку данных
        DataService realDataService = new DataServiceImpl();

        // Оборачиваем реальный сервис в кэширующий прокси
        DataService cachingProxy = new CachingDataServiceProxy(realDataService);

        // Первый запрос "param1" — кэш пуст, данные загружаются из реального сервиса (~3 сек)
        System.out.println("First request:");
        String data1 = cachingProxy.fetchData("param1");
        System.out.println("Received: " + data1);

        System.out.println();

        // Повторный запрос "param1" — данные уже в кэше, возвращаются мгновенно
        System.out.println("Second request:");
        String data2 = cachingProxy.fetchData("param1");
        System.out.println("Received: " + data2);

        System.out.println();

        // Первый запрос "param2" — новый ключ, данные загружаются из реального сервиса
        System.out.println("Third request:");
        String data3 = cachingProxy.fetchData("param2");
        System.out.println("Received: " + data3);

        System.out.println();

        // Повторный запрос "param2" — данные уже в кэше
        System.out.println("Fourth request:");
        String data4 = cachingProxy.fetchData("param2");
        System.out.println("Received: " + data4);
    }
}
