package pattern3_behavior.behavior1_chain.code.example3_validation;

/**
 * <p><b>Валидатор CSV-документов</b> — конкретное звено цепочки обязанностей.</p>
 *
 * <p>Отвечает за валидацию документов типа {@code "CSV"}.
 * Если тип переданного документа ({@link Document#getType()}) совпадает
 * с {@code "CSV"}, валидатор выполняет проверку содержимого.
 * В противном случае документ передаётся следующему валидатору в цепочке.</p>
 *
 * @see BaseValidator
 * @see JsonValidator
 * @see XmlValidator
 * @see TerminalValidator
 */
public class CsvValidator extends BaseValidator {

    /**
     * {@inheritDoc}
     *
     * <p>Проверяет, является ли тип документа {@code "CSV"}
     * (без учёта регистра).</p>
     *
     * @param document документ для проверки
     * @return {@code true}, если тип документа — {@code "CSV"};
     *         {@code false} в противном случае
     */
    @Override
    protected boolean canValidate(Document document) {
        return "CSV".equalsIgnoreCase(document.getType());
    }

    /**
     * {@inheritDoc}
     *
     * <p>Выполняет валидацию CSV-документа и выводит сообщение
     * об успешной проверке.</p>
     *
     * @param document CSV-документ для валидации
     */
    @Override
    protected void doValidate(Document document) {
        System.out.println("CsvValidator: документ типа CSV успешно провалидирован. " +
                "Содержимое: " + document.getContent());
    }
}
