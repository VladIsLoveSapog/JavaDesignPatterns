package pattern3_behavior.behavior1_chain.code.example2_web;

import java.util.Objects;

/**
 * <p><b>Обработчик авторизации</b> — конкретное звено цепочки обязанностей.</p>
 *
 * <p>Отвечает за проверку прав доступа. Если входящий запрос
 * соответствует команде {@code "authorize"}, обработчик выполняет
 * авторизацию и передаёт запрос {@code "log"} следующему обработчику
 * в цепочке ({@link LoggingHandler}).</p>
 *
 * <p>Если запрос не относится к авторизации, он передаётся дальше
 * по цепочке без изменений.</p>
 *
 * @see AbstractHandler
 * @see AuthenticationHandler
 * @see LoggingHandler
 */
public class AuthorizationHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     *
     * <p>Проверяет, является ли запрос командой авторизации.
     * При успешной авторизации передаёт команду {@code "log"}
     * следующему обработчику. В противном случае пробрасывает
     * исходный запрос дальше по цепочке.</p>
     *
     * @param request строковое представление запроса
     */
    @Override
    public void handleRequest(String request) {
        if (request.equalsIgnoreCase("authorize")) {
            System.out.println("AuthorizationHandler: Authorized the request.");
            if (Objects.nonNull(nextHandler)) nextHandler.handleRequest("log");
        } else {
            System.out.println("AuthorizationHandler: Cannot handle the request. Passing to next handler.");
            if (Objects.nonNull(nextHandler)) nextHandler.handleRequest(request);
        }
    }
}
