package pattern2_structural.struct6_proxy.code.example1_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Паттерн Заместитель (Proxy) — кэширующий прокси (Caching Proxy).
 * <p>
 * Перехватывает вызовы к реальному сервису {@link DataServiceImpl} и сохраняет
 * результаты в локальный кэш ({@link HashMap}). При повторном запросе с тем же
 * параметром данные возвращаются из кэша, минуя дорогостоящую операцию загрузки.
 * <p>
 * Реализует тот же интерфейс {@link DataService}, что и реальный сервис,
 * поэтому клиент может работать с прокси прозрачно, не зная о кэшировании.
 */
public class CachingDataServiceProxy implements DataService {

    /** Ссылка на реальный сервис, к которому делегируются запросы при отсутствии данных в кэше */
    private DataService realDataService;

    /** Локальный кэш: ключ — параметр запроса, значение — полученные данные */
    private Map<String, String> cache;

    /**
     * Создаёт кэширующий прокси для указанного сервиса данных.
     *
     * @param realDataService реальный сервис, вызовы к которому будут кэшироваться
     */
    public CachingDataServiceProxy(DataService realDataService) {
        this.realDataService = realDataService;
        this.cache = new HashMap<>();
    }

    /**
     * Загружает данные по указанному параметру, используя кэш.
     * <p>
     * Алгоритм:
     * 1. Проверяется наличие данных в кэше по ключу {@code parameter}.
     * 2. Если данные найдены — возвращаются из кэша (без обращения к реальному сервису).
     * 3. Если данных нет — запрос делегируется реальному сервису, результат сохраняется в кэш.
     *
     * @param parameter ключ, по которому запрашиваются данные
     * @return строковое представление данных (из кэша или от реального сервиса)
     */
    @Override
    public String fetchData(String parameter) {
        // Шаг 1: проверяем наличие данных в кэше
        if (cache.containsKey(parameter)) {
            System.out.println("Returning cached data for: " + parameter);
            return cache.get(parameter);
        }

        // Шаг 2: данных нет — делегируем запрос реальному сервису и кэшируем результат
        System.out.println("No cache found for: " + parameter + ". Fetching data...");
        String data = realDataService.fetchData(parameter);
        cache.put(parameter, data);
        return data;
    }
}
