package pattern3_behavior.behavior10_template_method.code.example3_document_generator;

/**
 * Генератор HTML-документов — <b>ConcreteClass</b> паттерна Template Method.
 *
 * <p>Реализует абстрактные шаги {@link #createTitle()} и {@link #createBody()},
 * формируя заголовок и тело в формате HTML.
 *
 * <p><b>Хук {@code addTimestamp()}</b> не переопределён, поэтому метка времени
 * добавляется к документу (поведение по умолчанию).
 *
 * @see AbstractDocumentGenerator
 */
public class HtmlDocumentGenerator extends AbstractDocumentGenerator {

    /**
     * Создаёт заголовок документа в формате HTML-тега {@code <h1>}.
     *
     * @return строка с HTML-заголовком
     */
    @Override
    protected String createTitle() {
        return "<h1>Отчёт (HTML)</h1>";
    }

    /**
     * Создаёт тело документа в формате HTML-параграфа {@code <p>}.
     *
     * @return строка с HTML-телом документа
     */
    @Override
    protected String createBody() {
        return "<p>Это тело HTML-документа.</p>";
    }

    // Хук addTimestamp() НЕ переопределён — метка времени будет добавлена
}
