package pattern1_creation.create3_abstract_factory.code.example1_furniture.baroque;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Sofa;

/**
 * Конкретный продукт — диван в стиле барокко.
 *
 * <p>Реализует интерфейс {@link Sofa} и представляет диван в стиле барокко.
 * Экземпляры создаются через фабрику {@link BaroqueFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record BaroqueSofa() implements Sofa {
}
