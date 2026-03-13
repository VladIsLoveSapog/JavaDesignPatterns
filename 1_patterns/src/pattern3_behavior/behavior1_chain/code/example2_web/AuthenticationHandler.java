package pattern3_behavior.behavior1_chain.code.example2_web;

import java.util.Objects;

/**
 * <p><b>Обработчик аутентификации</b> — конкретное звено цепочки обязанностей.</p>
 *
 * <p>Отвечает за проверку подлинности запроса. Если входящий запрос
 * соответствует команде {@code "authenticate"}, обработчик выполняет
 * аутентификацию и передаёт запрос {@code "authorize"} следующему
 * обработчику в цепочке ({@link AuthorizationHandler}).</p>
 *
 * <p>Если запрос не относится к аутентификации, он передаётся дальше
 * по цепочке без изменений.</p>
 *
 * @see AbstractHandler
 * @see AuthorizationHandler
 * @see LoggingHandler
 */
public class AuthenticationHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     *
     * <p>Проверяет, является ли запрос командой аутентификации.
     * При успешной аутентификации передаёт команду {@code "authorize"}
     * следующему обработчику. В противном случае пробрасывает
     * исходный запрос дальше по цепочке.</p>
     *
     * @param request строковое представление запроса
     */
    @Override
    public void handleRequest(String request) {
        if (request.equalsIgnoreCase("authenticate")) {
            System.out.println("AuthenticationHandler: Authenticated the request.");
            if (Objects.nonNull(nextHandler)) nextHandler.handleRequest("authorize");
        } else {
            System.out.println("AuthenticationHandler: Cannot handle the request. Passing to next handler.");
            if (Objects.nonNull(nextHandler)) nextHandler.handleRequest(request);
        }
    }
}
