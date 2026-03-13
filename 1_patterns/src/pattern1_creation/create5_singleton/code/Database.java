package pattern1_creation.create5_singleton.code;

import java.util.Objects;

/**
 * Практический пример синглтона — имитация класса доступа к базе данных.
 *
 * <p>В реальном приложении подключение к БД обычно должно быть единственным
 * (или управляться пулом соединений). Синглтон гарантирует, что все части
 * приложения работают через один и тот же объект-менеджер БД.
 *
 * <p><b>Реализация:</b> ленивая инициализация (Lazy Initialization), не потокобезопасна.
 * Для многопоточной среды используйте подходы из примеров 3–6.
 */
public class Database {
    private static Database instance = null;    //одна единственная ссылка на класс

    /** Приватный конструктор — запрещает создание экземпляров извне. */
    private Database() {
    }

    /**
     * Получение ссылки на объект
     *
     * @return ссылку на единственный экземпляр класса
     */
    public static Database getInstance() {
        if (Objects.isNull(instance)) instance = new Database();
        return instance;
    }

    /**
     * Имитация выполнения SQL-запроса.
     *
     * @param sql текст запроса
     * @return результат (в данном случае — эхо запроса)
     */
    public String query(String sql) {
        return sql;
    }
}
