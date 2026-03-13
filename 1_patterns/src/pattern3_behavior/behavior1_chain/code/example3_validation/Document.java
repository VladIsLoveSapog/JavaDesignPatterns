package pattern3_behavior.behavior1_chain.code.example3_validation;

/**
 * <p><b>Запрос (Request)</b> в паттерне «Цепочка обязанностей».</p>
 *
 * <p>Представляет документ, который необходимо провалидировать.
 * Каждый документ характеризуется типом ({@code "JSON"}, {@code "XML"},
 * {@code "CSV"}, {@code "TXT"} и т.д.) и текстовым содержимым.</p>
 *
 * <p>Объект {@code Document} передаётся по цепочке валидаторов
 * ({@link DocumentValidator}). Каждый валидатор проверяет, способен ли
 * он обработать документ данного типа, и при совпадении выполняет
 * валидацию содержимого.</p>
 *
 * @see DocumentValidator
 * @see BaseValidator
 * @see TerminalValidator
 */
public class Document {

    /** Тип документа (например, {@code "JSON"}, {@code "XML"}, {@code "CSV"}). */
    private final String type;

    /** Текстовое содержимое документа. */
    private final String content;

    /**
     * Создаёт новый документ с указанным типом и содержимым.
     *
     * @param type    тип документа (например, {@code "JSON"}, {@code "XML"}, {@code "CSV"})
     * @param content текстовое содержимое документа
     */
    public Document(String type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * Возвращает тип документа.
     *
     * @return строковое представление типа документа
     */
    public String getType() {
        return type;
    }

    /**
     * Возвращает содержимое документа.
     *
     * @return текстовое содержимое документа
     */
    public String getContent() {
        return content;
    }

    /**
     * Возвращает строковое представление документа, включающее тип и содержимое.
     *
     * @return строка вида {@code "Document{type='JSON', content='...'}"}
     */
    @Override
    public String toString() {
        return "Document{type='" + type + "', content='" + content + "'}";
    }
}
