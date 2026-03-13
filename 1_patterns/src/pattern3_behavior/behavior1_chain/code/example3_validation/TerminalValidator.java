package pattern3_behavior.behavior1_chain.code.example3_validation;

/**
 * <p><b>Терминальный валидатор</b> — завершающее звено цепочки обязанностей.</p>
 *
 * <p>Располагается в самом конце цепочки валидаторов и служит «предохранителем»:
 * если ни один из предыдущих валидаторов ({@link JsonValidator},
 * {@link XmlValidator}, {@link CsvValidator}) не смог обработать документ,
 * терминальный валидатор <b>всегда</b> выбрасывает исключение
 * {@link UnsupportedDocumentException}.</p>
 *
 * <p>Такой подход гарантирует, что неподдерживаемые типы документов
 * не будут молча проигнорированы: вместо этого вызывающий код получит
 * явное уведомление об ошибке.</p>
 *
 * <p>Терминальный валидатор <b>не передаёт</b> документ следующему звену,
 * даже если оно установлено, поскольку его задача — прервать цепочку
 * с ошибкой.</p>
 *
 * @see DocumentValidator
 * @see BaseValidator
 * @see UnsupportedDocumentException
 */
public class TerminalValidator implements DocumentValidator {

    /**
     * <p>Установка следующего валидатора не имеет эффекта, так как
     * терминальный валидатор всегда прерывает цепочку.</p>
     *
     * @param next следующий валидатор (игнорируется)
     */
    @Override
    public void setNext(DocumentValidator next) {
        // Терминальный узел не передаёт управление дальше — метод намеренно пуст
    }

    /**
     * Выбрасывает {@link UnsupportedDocumentException} для любого документа.
     *
     * <p>Этот метод вызывается, когда ни один валидатор в цепочке
     * не смог обработать переданный документ. Вместо молчаливого
     * игнорирования терминальный валидатор сигнализирует об ошибке,
     * выбрасывая непроверяемое исключение.</p>
     *
     * @param document документ, тип которого не поддерживается
     * @throws UnsupportedDocumentException всегда
     */
    @Override
    public void validate(Document document) {
        throw new UnsupportedDocumentException(document);
    }
}
