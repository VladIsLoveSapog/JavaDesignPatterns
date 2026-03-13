package pattern1_creation.create7_object_pool.code.example1_connection_pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Пул соединений к базе данных — реализация паттерна Object Pool.
 *
 * <p>Управляет коллекцией объектов {@link DatabaseConnection}, предоставляя
 * клиентам свободные соединения и принимая их обратно после использования.
 *
 * <p>Пул создаёт начальный набор соединений при инициализации и может
 * динамически расширяться до {@code maxSize}. Если все соединения заняты и лимит достигнут, выбрасывается
 * {@link RuntimeException}.
 *
 * <p><b>Потокобезопасность:</b> все публичные методы синхронизированы,
 * что позволяет безопасно использовать пул из нескольких потоков.
 *
 * <p><b>Синглтон:</b> пул реализован как синглтон, чтобы гарантировать
 * единую точку управления соединениями в приложении.
 */
public class ConnectionPool {
    private List<DatabaseConnection> pool;
    private int maxSize;

    /**
     * Единственный экземпляр пула (паттерн Singleton).
     */
    private static ConnectionPool instance;

    /**
     * Приватный конструктор — создаёт пул с начальным набором соединений.
     *
     * @param initialSize количество соединений, создаваемых при инициализации
     * @param maxSize     максимально допустимое количество соединений в пуле
     */
    private ConnectionPool(int initialSize, int maxSize) {
        this.pool = new ArrayList<>();
        this.maxSize = maxSize;
        for (int i = 1; i <= initialSize; i++) {
            pool.add(new DatabaseConnection("Conn-" + i));
        }
    }

    /**
     * Возвращает единственный экземпляр пула соединений.
     *
     * <p>При первом вызове создаёт пул с указанными параметрами.
     * При последующих вызовах параметры игнорируются.
     *
     * @param initialSize начальное количество соединений
     * @param maxSize     максимальный размер пула
     * @return единственный экземпляр {@code ConnectionPool}
     */
    public static synchronized ConnectionPool getInstance(int initialSize, int maxSize) {
        if (instance == null) {
            instance = new ConnectionPool(initialSize, maxSize);
        }
        return instance;
    }

    /**
     * Сбрасывает экземпляр пула (для использования в демонстрационных целях).
     */
    static synchronized void resetInstance() {
        instance = null;
    }

    /**
     * Выдаёт свободное соединение из пула.
     *
     * <p>Сначала ищет неиспользуемое соединение среди существующих.
     * Если свободных нет и размер пула меньше {@code maxSize},
     * создаёт новое соединение. Если пул исчерпан — выбрасывает исключение.
     *
     * @return свободное соединение {@link DatabaseConnection}
     * @throws RuntimeException если все соединения заняты и пул достиг максимального размера
     */
    public synchronized DatabaseConnection borrowConnection() {
        for (DatabaseConnection conn : pool) {
            if (!conn.isInUse()) {
                conn.connect();
                return conn;
            }
        }
        if (pool.size() < maxSize) {
            DatabaseConnection newConn = new DatabaseConnection("Conn-" + (pool.size() + 1));
            newConn.connect();
            pool.add(newConn);
            return newConn;
        }
        throw new RuntimeException("Все соединения в пуле заняты.");
    }

    /**
     * Возвращает соединение обратно в пул.
     *
     * <p>Вызывает {@link DatabaseConnection#reset()}, чтобы сбросить
     * состояние соединения перед повторной выдачей.
     *
     * @param conn соединение для возврата
     * @throws IllegalArgumentException если соединение не принадлежит данному пулу
     */
    public synchronized void returnConnection(DatabaseConnection conn) {
        if (pool.contains(conn)) {
            conn.reset();
        } else {
            throw new IllegalArgumentException("Соединение не принадлежит пулу.");
        }
    }

    /**
     * Выводит текущее состояние всех соединений в пуле.
     */
    public synchronized void printStatus() {
        System.out.println("Текущее состояние пула соединений:");
        for (DatabaseConnection conn : pool) {
            System.out.println("  " + conn.getConnectionId() + " — "
                    + (conn.isInUse() ? "Используется" : "Свободно"));
        }
    }
}
