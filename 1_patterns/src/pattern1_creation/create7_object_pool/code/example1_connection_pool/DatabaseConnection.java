package pattern1_creation.create7_object_pool.code.example1_connection_pool;

import pattern1_creation.create7_object_pool.code.Reusable;

/**
 * Конкретный повторно используемый объект — имитация соединения с базой данных.
 *
 * <p>В реальных приложениях создание соединения с БД — дорогая операция
 * (установка TCP-соединения, аутентификация, выделение ресурсов на сервере).
 * Пул соединений позволяет переиспользовать уже открытые соединения,
 * значительно снижая накладные расходы.
 *
 * <p>Реализует {@link Reusable}, чтобы пул мог сбрасывать состояние
 * соединения перед повторной выдачей.
 */
public class DatabaseConnection implements Reusable {
    private boolean inUse;
    private String connectionId;

    /**
     * Создаёт новое соединение с указанным идентификатором.
     *
     * <p>Симулирует дорогостоящую операцию установки соединения.
     *
     * @param connectionId уникальный идентификатор соединения
     */
    public DatabaseConnection(String connectionId) {
        this.connectionId = connectionId;
        this.inUse = false;
        System.out.println("Соединение " + connectionId + " установлено.");
    }

    /**
     * Проверяет, используется ли соединение в данный момент.
     *
     * @return {@code true}, если соединение занято клиентом
     */
    public boolean isInUse() {
        return inUse;
    }

    /**
     * Занимает соединение для использования клиентом.
     *
     * <p>Если соединение уже занято, выводит предупреждение.
     */
    public void connect() {
        if (!inUse) {
            inUse = true;
            System.out.println("Соединение " + connectionId + " используется.");
        } else {
            System.out.println("Соединение " + connectionId + " уже используется.");
        }
    }

    /**
     * Освобождает соединение, делая его доступным для повторного использования.
     */
    public void disconnect() {
        if (inUse) {
            inUse = false;
            System.out.println("Соединение " + connectionId + " освобождено.");
        }
    }

    /**
     * Сбрасывает состояние соединения.
     *
     * <p>Вызывает {@link #disconnect()} и выполняет дополнительную очистку.
     * Используется пулом при возврате соединения.
     */
    @Override
    public void reset() {
        disconnect();
        System.out.println("Соединение " + connectionId + " сброшено.");
    }

    /**
     * Возвращает идентификатор соединения.
     *
     * @return идентификатор соединения
     */
    public String getConnectionId() {
        return connectionId;
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "id='" + connectionId + '\'' +
                ", inUse=" + inUse +
                '}';
    }
}
