package pattern3_behavior.behavior1_chain.code.example3_validation;

/**
 * <p><b>Валидатор XML-документов</b> — конкретное звено цепочки обязанностей.</p>
 *
 * <p>Отвечает за валидацию документов типа {@code "XML"}.
 * Если тип переданного документа ({@link Document#getType()}) совпадает
 * с {@code "XML"}, валидатор выполняет проверку содержимого.
 * В противном случае документ передаётся следующему валидатору в цепочке.</p>
 *
 * @see BaseValidator
 * @see JsonValidator
 * @see CsvValidator
 * @see TerminalValidator
 */
public class XmlValidator extends BaseValidator {

    /**
     * {@inheritDoc}
     *
     * <p>Проверяет, является ли тип документа {@code "XML"}
     * (без учёта регистра).</p>
     *
     * @param document документ для проверки
     * @return {@code true}, если тип документа — {@code "XML"};
     *         {@code false} в противном случае
     */
    @Override
    protected boolean canValidate(Document document) {
        return "XML".equalsIgnoreCase(document.getType());
    }

    /**
     * {@inheritDoc}
     *
     * <p>Выполняет валидацию XML-документа и выводит сообщение
     * об успешной проверке.</p>
     *
     * @param document XML-документ для валидации
     */
    @Override
    protected void doValidate(Document document) {
        System.out.println("XmlValidator: документ типа XML успешно провалидирован. " +
                "Содержимое: " + document.getContent());
    }
}
