package pattern1_creation.create3_abstract_factory.code;

/**
 * [AbstractProduct]
 * Общий интерфейс диванов.
 *
 * <p>Интерфейс пустой, потому что это учебный пример — в реальном коде здесь
 * были бы методы вроде {@code getStyle()} или {@code describe()}, общие для всех
 * реализаций дивана. Клиентский код работает только с этим типом, не зная о
 * конкретных классах {@code BaroqueSofa}, {@code GoticSofa} и т.д.
 */
public interface Sofa {
}
