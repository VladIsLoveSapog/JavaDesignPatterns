package pattern1_creation.create3_abstract_factory.code;

/**
 * [AbstractProduct]
 * Общий интерфейс столов.
 *
 * <p>Интерфейс пустой, потому что это учебный пример — в реальном коде здесь
 * были бы методы вроде {@code getStyle()} или {@code describe()}, общие для всех
 * реализаций стола. Клиентский код работает только с этим типом, не зная о
 * конкретных классах {@code BaroqueTable}, {@code GoticTable} и т.д.
 */
public interface Table {
}
