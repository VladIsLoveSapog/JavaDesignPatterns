package pattern1_creation.create3_abstract_factory.code.baroque;

import pattern1_creation.create3_abstract_factory.code.AbstractFurnitureFactory;
import pattern1_creation.create3_abstract_factory.code.Chair;
import pattern1_creation.create3_abstract_factory.code.Sofa;
import pattern1_creation.create3_abstract_factory.code.Table;

/**
 * [ConcreteFactory]
 * Фабрика мебели в стиле барокко.
 *
 * <p>Внутри методов возвращаем конкретные типы ({@link BaroqueChair} и т.д.),
 * но сигнатура методов использует абстрактные типы ({@code Chair}, {@code Sofa},
 * {@code Table}) — клиент не зависит от конкретных реализаций.
 */
public class BaroqueFurnitureFactory implements AbstractFurnitureFactory {
    /**
     * Создание дивана в стиле барокко
     *
     * @return диван в стиле барокко
     */
    @Override
    public Sofa createSofa() {
        return new BaroqueSofa();
    }

    /**
     * Создание кресла в стиле барокко
     *
     * @return кресло в стиле барокко
     */
    @Override
    public Chair createChair() {
        return new BaroqueChair();
    }

    /**
     * Создание стола в стиле барокко
     *
     * @return стол в стиле барокко
     */
    @Override
    public Table createTable() {
        return new BaroqueTable();
    }
}
