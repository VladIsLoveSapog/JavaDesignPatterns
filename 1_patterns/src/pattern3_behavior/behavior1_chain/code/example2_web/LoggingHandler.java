package pattern3_behavior.behavior1_chain.code.example2_web;

/**
 * <p><b>Обработчик логирования</b> — конечное звено цепочки обязанностей.</p>
 *
 * <p>Отвечает за запись (логирование) запроса. Если входящий запрос
 * соответствует команде {@code "log"}, обработчик выполняет логирование.
 * В противном случае сообщает о невозможности обработки.</p>
 *
 * <p>Как правило, является последним обработчиком в цепочке и не передаёт
 * запрос дальше.</p>
 *
 * @see AbstractHandler
 * @see AuthenticationHandler
 * @see AuthorizationHandler
 */
public class LoggingHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     *
     * <p>Проверяет, является ли запрос командой логирования.
     * При совпадении выполняет запись лога. Если запрос не распознан,
     * выводит сообщение о невозможности обработки.</p>
     *
     * @param request строковое представление запроса
     */
    @Override
    public void handleRequest(String request) {
        if (request.equalsIgnoreCase("log")) System.out.println("LoggingHandler: Logged the request.");
        else System.out.println("LoggingHandler: Cannot handle the request.");
    }
}
