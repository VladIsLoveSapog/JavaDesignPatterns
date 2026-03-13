package pattern1_creation.create3_abstract_factory.code.example1_furniture.baroque;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.AbstractFurnitureFactory;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Chair;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Sofa;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Table;

/**
 * Фабрика мебели в стиле барокко.
 *
 * <p>Реализует {@link AbstractFurnitureFactory} и создаёт все продукты в стиле барокко:
 * {@link BaroqueChair}, {@link BaroqueSofa}, {@link BaroqueTable}. Сигнатуры методов
 * возвращают абстрактные типы ({@code Chair}, {@code Sofa}, {@code Table}),
 * что обеспечивает слабую связанность с клиентским кодом.</p>
 *
 * @see AbstractFurnitureFactory
 */
public class BaroqueFurnitureFactory implements AbstractFurnitureFactory {

    /**
     * Создаёт диван в стиле барокко.
     *
     * @return новый экземпляр {@link BaroqueSofa}
     */
    @Override
    public Sofa createSofa() {
        return new BaroqueSofa();
    }

    /**
     * Создаёт кресло в стиле барокко.
     *
     * @return новый экземпляр {@link BaroqueChair}
     */
    @Override
    public Chair createChair() {
        return new BaroqueChair();
    }

    /**
     * Создаёт стол в стиле барокко.
     *
     * @return новый экземпляр {@link BaroqueTable}
     */
    @Override
    public Table createTable() {
        return new BaroqueTable();
    }
}
