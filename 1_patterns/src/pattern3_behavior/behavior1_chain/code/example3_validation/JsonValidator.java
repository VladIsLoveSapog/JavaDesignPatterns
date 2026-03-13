package pattern3_behavior.behavior1_chain.code.example3_validation;

/**
 * <p><b>Валидатор JSON-документов</b> — конкретное звено цепочки обязанностей.</p>
 *
 * <p>Отвечает за валидацию документов типа {@code "JSON"}.
 * Если тип переданного документа ({@link Document#getType()}) совпадает
 * с {@code "JSON"}, валидатор выполняет проверку содержимого.
 * В противном случае документ передаётся следующему валидатору в цепочке.</p>
 *
 * @see BaseValidator
 * @see XmlValidator
 * @see CsvValidator
 * @see TerminalValidator
 */
public class JsonValidator extends BaseValidator {

    /**
     * {@inheritDoc}
     *
     * <p>Проверяет, является ли тип документа {@code "JSON"}
     * (без учёта регистра).</p>
     *
     * @param document документ для проверки
     * @return {@code true}, если тип документа — {@code "JSON"};
     *         {@code false} в противном случае
     */
    @Override
    protected boolean canValidate(Document document) {
        return "JSON".equalsIgnoreCase(document.getType());
    }

    /**
     * {@inheritDoc}
     *
     * <p>Выполняет валидацию JSON-документа и выводит сообщение
     * об успешной проверке.</p>
     *
     * @param document JSON-документ для валидации
     */
    @Override
    protected void doValidate(Document document) {
        System.out.println("JsonValidator: документ типа JSON успешно провалидирован. " +
                "Содержимое: " + document.getContent());
    }
}
