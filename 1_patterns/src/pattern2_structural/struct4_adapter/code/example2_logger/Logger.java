package pattern2_structural.struct4_adapter.code.example2_logger;

/**
 * Целевой интерфейс (Target) — контракт логирования в нашей системе.
 *
 * <p>Вся наша кодовая база использует этот интерфейс для записи логов.
 * Он предоставляет отдельные методы для каждого уровня важности:
 * {@link #logInfo(String)} и {@link #logError(String)}.
 *
 * <p>Проблема: мы хотим подключить новую систему логирования ({@link NewLogger}),
 * которая имеет совершенно другой интерфейс — один метод {@code log(severity, message)}.
 * Переписывать весь существующий код, использующий {@code Logger}, — дорого и рискованно.
 * Решение — адаптер.
 */
public interface Logger {

    /**
     * Записать информационное сообщение.
     *
     * @param message текст сообщения
     */
    void logInfo(String message);

    /**
     * Записать сообщение об ошибке.
     *
     * @param message текст сообщения об ошибке
     */
    void logError(String message);
}
