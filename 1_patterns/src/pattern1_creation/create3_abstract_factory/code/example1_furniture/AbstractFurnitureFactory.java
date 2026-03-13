package pattern1_creation.create3_abstract_factory.code.example1_furniture;

/**
 * Интерфейс абстрактной фабрики мебели.
 *
 * <p>Определяет контракт для создания семейства связанных продуктов:
 * {@link Chair}, {@link Sofa}, {@link Table}. Наличие всех трёх методов в одном интерфейсе
 * гарантирует согласованность семейства — реализация обязана предоставить кресло, диван
 * и стол одного стиля, смешать стили невозможно на уровне типов.</p>
 *
 * <p>В методах используются общие типы, что позволяет достичь слабой
 * связанности — клиент не привязывается к конкретным реализациям классов.</p>
 *
 * <h3>Пример использования</h3>
 * <pre>{@code
 * AbstractFurnitureFactory factory = new ModernFurnitureFactory();
 * Chair chair = factory.createChair();
 * Sofa sofa = factory.createSofa();
 * }</pre>
 *
 * @see Chair
 * @see Sofa
 * @see Table
 */
public interface AbstractFurnitureFactory {
    /**
     * Создаёт новый экземпляр дивана в стиле данной фабрики.
     *
     * @return новый экземпляр {@link Sofa}
     */
    Sofa createSofa();

    /**
     * Создаёт новый экземпляр кресла в стиле данной фабрики.
     *
     * @return новый экземпляр {@link Chair}
     */
    Chair createChair();

    /**
     * Создаёт новый экземпляр стола в стиле данной фабрики.
     *
     * @return новый экземпляр {@link Table}
     */
    Table createTable();
}
