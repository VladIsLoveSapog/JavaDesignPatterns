package pattern3_behavior.behavior10_template_method.code.example3_document_generator;

/**
 * Генератор текстовых документов — <b>ConcreteClass</b> паттерна Template Method.
 *
 * <p>Реализует абстрактные шаги {@link #createTitle()} и {@link #createBody()},
 * формируя заголовок и тело в простом текстовом формате.
 *
 * <p><b>Хук {@code addTimestamp()}</b> переопределён и возвращает {@code false},
 * поэтому метка времени НЕ добавляется к документу. Это демонстрирует,
 * как хуки позволяют подклассам влиять на поведение шаблонного метода,
 * не изменяя его структуру.
 *
 * @see AbstractDocumentGenerator
 */
public class PlainTextDocumentGenerator extends AbstractDocumentGenerator {

    /**
     * Создаёт заголовок документа в простом текстовом формате.
     *
     * @return строка с текстовым заголовком
     */
    @Override
    protected String createTitle() {
        return "ОТЧЁТ (Plain Text)";
    }

    /**
     * Создаёт тело документа в простом текстовом формате.
     *
     * @return строка с текстовым телом документа
     */
    @Override
    protected String createBody() {
        return "Это тело текстового документа.";
    }

    /**
     * Переопределённый хук — отключает добавление метки времени.
     *
     * @return {@code false} — метка времени не добавляется
     */
    @Override
    protected boolean addTimestamp() {
        return false;
    }
}
