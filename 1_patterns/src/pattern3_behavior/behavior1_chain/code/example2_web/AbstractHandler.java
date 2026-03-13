package pattern3_behavior.behavior1_chain.code.example2_web;

/**
 * <p><b>Абстрактный обработчик</b> в паттерне «Цепочка обязанностей».</p>
 *
 * <p>Предоставляет базовую реализацию интерфейса {@link Handler},
 * инкапсулируя логику хранения ссылки на следующий обработчик в цепочке.
 * Конкретные обработчики наследуют этот класс и реализуют метод
 * {@link #handleRequest(String)}, определяя собственную логику обработки запроса.</p>
 *
 * <p>Использование абстрактного класса позволяет избежать дублирования кода
 * в каждом конкретном обработчике: общая логика установки следующего звена
 * цепочки реализована здесь один раз.</p>
 *
 * @see Handler
 * @see AuthenticationHandler
 * @see AuthorizationHandler
 * @see LoggingHandler
 */
public abstract class AbstractHandler implements Handler {

    /** Ссылка на следующий обработчик в цепочке. */
    protected Handler nextHandler;

    /**
     * {@inheritDoc}
     *
     * <p>Сохраняет ссылку на следующий обработчик, которому будет передан
     * запрос, если текущий обработчик не может его обработать самостоятельно.</p>
     *
     * @param handler следующий обработчик в цепочке
     */
    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }
}
