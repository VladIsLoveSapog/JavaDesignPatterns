package pattern1_creation.create7_object_pool.code.example1_connection_pool;

/**
 * Демонстрация паттерна Object Pool на примере пула соединений к БД.
 *
 * <p>Программа показывает основные сценарии работы с пулом:
 * <ul>
 *   <li>Создание пула с начальным набором соединений</li>
 *   <li>Получение (borrow) соединений из пула</li>
 *   <li>Возврат (return) соединений обратно в пул</li>
 *   <li>Динамическое расширение пула при нехватке соединений</li>
 *   <li>Обработка ситуации, когда пул исчерпан</li>
 * </ul>
 *
 * @see ConnectionPool
 * @see DatabaseConnection
 */
public class ConnectionPoolMain {
    public static void main(String[] args) {
        // Сбрасываем синглтон на случай повторного запуска в одной JVM
        ConnectionPool.resetInstance();

        // Создаём пул: 2 начальных соединения, максимум 3
        ConnectionPool pool = ConnectionPool.getInstance(2, 3);
        System.out.println();

        // === 1. Получение соединений из пула ===
        System.out.println("=== Получение соединений ===");
        DatabaseConnection conn1 = pool.borrowConnection();
        DatabaseConnection conn2 = pool.borrowConnection();
        pool.printStatus();
        System.out.println();

        // === 2. Возврат соединения и повторное использование ===
        System.out.println("=== Возврат conn1 в пул ===");
        pool.returnConnection(conn1);
        pool.printStatus();
        System.out.println();

        // === 3. Повторное получение — должен вернуться тот же объект ===
        System.out.println("=== Повторное получение соединения ===");
        DatabaseConnection conn3 = pool.borrowConnection();
        System.out.println("conn1 и conn3 — один объект? " + (conn1 == conn3));
        pool.printStatus();
        System.out.println();

        // === 4. Динамическое расширение пула ===
        System.out.println("=== Динамическое расширение пула (запрос 3-го соединения) ===");
        DatabaseConnection conn4 = pool.borrowConnection();
        pool.printStatus();
        System.out.println();

        // === 5. Исчерпание пула ===
        System.out.println("=== Попытка получить соединение из исчерпанного пула ===");
        try {
            pool.borrowConnection();
        } catch (RuntimeException e) {
            System.out.println("Исключение: " + e.getMessage());
        }
    }
}
