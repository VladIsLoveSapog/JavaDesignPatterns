package pattern1_creation.create3_abstract_factory.code.example1_furniture.modern;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.AbstractFurnitureFactory;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Chair;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Sofa;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Table;

/**
 * Фабрика мебели в стиле модерн.
 *
 * <p>Реализует {@link AbstractFurnitureFactory} и создаёт все продукты в стиле модерн:
 * {@link ModernChair}, {@link ModernSofa}, {@link ModernTable}. Сигнатуры методов
 * возвращают абстрактные типы ({@code Chair}, {@code Sofa}, {@code Table}),
 * что обеспечивает слабую связанность с клиентским кодом.</p>
 *
 * @see AbstractFurnitureFactory
 */
public class ModernFurnitureFactory implements AbstractFurnitureFactory {

    /**
     * Создаёт диван в стиле модерн.
     *
     * @return новый экземпляр {@link ModernSofa}
     */
    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    /**
     * Создаёт кресло в стиле модерн.
     *
     * @return новый экземпляр {@link ModernChair}
     */
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    /**
     * Создаёт стол в стиле модерн.
     *
     * @return новый экземпляр {@link ModernTable}
     */
    @Override
    public Table createTable() {
        return new ModernTable();
    }
}
