package pattern1_creation.create3_abstract_factory.code.example1_furniture.gotik;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.AbstractFurnitureFactory;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Chair;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Sofa;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.Table;

/**
 * Фабрика мебели в стиле готика.
 *
 * <p>Реализует {@link AbstractFurnitureFactory} и создаёт все продукты в стиле готика:
 * {@link GoticChair}, {@link GoticSofa}, {@link GoticTable}. Сигнатуры методов
 * возвращают абстрактные типы ({@code Chair}, {@code Sofa}, {@code Table}),
 * что обеспечивает слабую связанность с клиентским кодом.</p>
 *
 * @see AbstractFurnitureFactory
 */
public class GoticFurnitureFactory implements AbstractFurnitureFactory {

    /**
     * Создаёт диван в стиле готика.
     *
     * @return новый экземпляр {@link GoticSofa}
     */
    @Override
    public Sofa createSofa() {
        return new GoticSofa();
    }

    /**
     * Создаёт кресло в стиле готика.
     *
     * @return новый экземпляр {@link GoticChair}
     */
    @Override
    public Chair createChair() {
        return new GoticChair();
    }

    /**
     * Создаёт стол в стиле готика.
     *
     * @return новый экземпляр {@link GoticTable}
     */
    @Override
    public Table createTable() {
        return new GoticTable();
    }
}
