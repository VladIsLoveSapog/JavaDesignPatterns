package pattern3_behavior.behavior1_chain.code.example2_web;

/**
 * <p><b>Демонстрация паттерна «Цепочка обязанностей»</b> на примере
 * обработки веб-запросов.</p>
 *
 * <p>Создаёт цепочку обработчиков:
 * {@link AuthenticationHandler} &rarr; {@link AuthorizationHandler} &rarr;
 * {@link LoggingHandler} и демонстрирует несколько сценариев:</p>
 * <ul>
 *     <li>Успешное прохождение запроса через всю цепочку (аутентификация &rarr;
 *         авторизация &rarr; логирование).</li>
 *     <li>Передача неизвестного запроса, который ни один обработчик
 *         не может обработать.</li>
 *     <li>Отправка запроса напрямую в {@link AuthorizationHandler},
 *         минуя аутентификацию, — демонстрирует, что цепочку можно
 *         начать с любого узла.</li>
 * </ul>
 *
 * @see Handler
 * @see AbstractHandler
 */
public class Main {

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создание обработчиков
        Handler authHandler = new AuthenticationHandler();
        Handler authorizationHandler = new AuthorizationHandler();
        Handler loggingHandler = new LoggingHandler();

        // Настройка цепочки: Authentication -> Authorization -> Logging
        authHandler.setNextHandler(authorizationHandler);
        authorizationHandler.setNextHandler(loggingHandler);

        // Сценарий 1: Отправка запроса 'authenticate' — проходит всю цепочку
        System.out.println("Client: Sending 'authenticate' request.");
        authHandler.handleRequest("authenticate");

        // Сценарий 2: Отправка неизвестного запроса
        System.out.println("\nClient: Sending 'unknown' request.");
        authHandler.handleRequest("unknown");

        // Сценарий 3: Отправка запроса напрямую в AuthorizationHandler,
        // минуя аутентификацию — демонстрирует, что цепочку можно начать с любого узла
        System.out.println("\nClient: Sending 'authorize' request directly to AuthorizationHandler (bypassing Authentication).");
        authorizationHandler.handleRequest("authorize");
    }
}
