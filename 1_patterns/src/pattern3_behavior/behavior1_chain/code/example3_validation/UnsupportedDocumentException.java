package pattern3_behavior.behavior1_chain.code.example3_validation;

/**
 * <p><b>Исключение для неподдерживаемых типов документов.</b></p>
 *
 * <p>Выбрасывается терминальным валидатором ({@link TerminalValidator}),
 * когда документ ({@link Document}) прошёл всю цепочку обязанностей,
 * но ни один валидатор не смог его обработать.</p>
 *
 * <p>Является непроверяемым исключением ({@link RuntimeException}),
 * поскольку неподдерживаемый тип документа считается ошибкой конфигурации
 * или некорректным вводом, а не штатной ситуацией, которую вызывающий код
 * обязан обрабатывать на этапе компиляции.</p>
 *
 * @see TerminalValidator
 * @see Document
 */
public class UnsupportedDocumentException extends RuntimeException {

    /**
     * Создаёт исключение с сообщением, содержащим тип неподдерживаемого документа.
     *
     * <p>Формат сообщения: {@code "Неподдерживаемый тип документа: <тип>"}.</p>
     *
     * @param document документ, тип которого не поддерживается ни одним
     *                 валидатором в цепочке
     */
    public UnsupportedDocumentException(Document document) {
        super("Неподдерживаемый тип документа: " + document.getType());
    }
}
