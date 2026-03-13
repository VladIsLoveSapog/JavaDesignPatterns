package pattern2_structural.struct6_proxy.code.example1_cache;

/**
 * Паттерн Заместитель (Proxy) — интерфейс сервиса данных (Subject).
 * <p>
 * Определяет общий интерфейс для реального сервиса {@link DataServiceImpl}
 * и его прокси-заместителя {@link CachingDataServiceProxy}.
 * Клиентский код работает с данными через этот интерфейс,
 * не зная, обращается ли он к реальному сервису или к кэширующему прокси.
 */
public interface DataService {

    /**
     * Загружает данные по указанному параметру.
     *
     * @param parameter ключ, по которому запрашиваются данные
     * @return строковое представление загруженных данных
     */
    String fetchData(String parameter);
}
