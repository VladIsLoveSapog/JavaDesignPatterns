package pattern3_behavior.behavior1_chain.code.example3_validation;

/**
 * <p><b>Абстрактный валидатор (Abstract Handler)</b> в паттерне «Цепочка обязанностей».</p>
 *
 * <p>Предоставляет базовую реализацию интерфейса {@link DocumentValidator},
 * инкапсулируя логику хранения ссылки на следующий валидатор в цепочке
 * и маршрутизации документа ({@link Document}) между звеньями.</p>
 *
 * <p>Конкретные валидаторы наследуют этот класс и реализуют два абстрактных метода:</p>
 * <ul>
 *     <li>{@link #canValidate(Document)} — определяет, способен ли данный валидатор
 *         обработать документ указанного типа;</li>
 *     <li>{@link #doValidate(Document)} — содержит непосредственную логику валидации.</li>
 * </ul>
 *
 * <p>Метод {@link #validate(Document)} реализует шаблон: если текущий валидатор
 * может обработать документ — вызывается {@link #doValidate(Document)},
 * иначе документ передаётся следующему валидатору в цепочке. Если следующего
 * валидатора нет, обработка завершается без действий.</p>
 *
 * @see DocumentValidator
 * @see JsonValidator
 * @see XmlValidator
 * @see CsvValidator
 * @see TerminalValidator
 */
public abstract class BaseValidator implements DocumentValidator {

    /** Ссылка на следующий валидатор в цепочке. */
    protected DocumentValidator nextValidator;

    /**
     * {@inheritDoc}
     *
     * <p>Сохраняет ссылку на следующий валидатор, которому будет передан
     * документ, если текущий валидатор не сможет его обработать самостоятельно.</p>
     *
     * @param next следующий валидатор в цепочке
     */
    @Override
    public void setNext(DocumentValidator next) {
        this.nextValidator = next;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Проверяет, способен ли текущий валидатор обработать документ
     * (через {@link #canValidate(Document)}). Если да — выполняет валидацию
     * (через {@link #doValidate(Document)}). Если нет — передаёт документ
     * следующему валидатору в цепочке. Если следующий валидатор отсутствует,
     * обработка завершается без действий.</p>
     *
     * @param document документ для валидации
     */
    @Override
    public void validate(Document document) {
        if (canValidate(document)) {
            doValidate(document);
        } else if (nextValidator != null) {
            nextValidator.validate(document);
        }
    }

    /**
     * Определяет, способен ли данный валидатор обработать указанный документ.
     *
     * <p>Конкретные валидаторы реализуют этот метод, проверяя тип документа
     * на соответствие поддерживаемому формату.</p>
     *
     * @param document документ для проверки
     * @return {@code true}, если валидатор может обработать данный документ;
     *         {@code false} в противном случае
     */
    protected abstract boolean canValidate(Document document);

    /**
     * Выполняет непосредственную валидацию документа.
     *
     * <p>Вызывается только в том случае, если {@link #canValidate(Document)}
     * вернул {@code true}. Конкретные валидаторы реализуют здесь
     * специфичную для формата логику проверки.</p>
     *
     * @param document документ для валидации
     */
    protected abstract void doValidate(Document document);
}
