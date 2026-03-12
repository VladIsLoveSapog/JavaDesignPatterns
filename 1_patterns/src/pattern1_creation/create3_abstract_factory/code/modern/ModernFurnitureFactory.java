package pattern1_creation.create3_abstract_factory.code.modern;

import pattern1_creation.create3_abstract_factory.code.AbstractFurnitureFactory;
import pattern1_creation.create3_abstract_factory.code.Chair;
import pattern1_creation.create3_abstract_factory.code.Sofa;
import pattern1_creation.create3_abstract_factory.code.Table;

/**
 * [ConcreteFactory]
 * Фабрика мебели в стиле модерн.
 *
 * <p>Внутри методов возвращаем конкретные типы ({@link ModernChair} и т.д.),
 * но сигнатура методов использует абстрактные типы ({@code Chair}, {@code Sofa},
 * {@code Table}) — клиент не зависит от конкретных реализаций.
 */
public class ModernFurnitureFactory implements AbstractFurnitureFactory {
    /**
     * Создание дивана в стиле модерн
     *
     * @return диван в стиле модерн
     */
    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    /**
     * Создание кресла в стиле модерн
     *
     * @return кресло в стиле модерн
     */
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    /**
     * Создание стола в стиле модерн
     *
     * @return стол в стиле модерн
     */
    @Override
    public Table createTable() {
        return new ModernTable();
    }
}