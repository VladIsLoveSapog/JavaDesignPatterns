package pattern1_creation.create3_abstract_factory.code.example1_furniture.modern;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Sofa;

/**
 * Конкретный продукт — диван в стиле модерн.
 *
 * <p>Реализует интерфейс {@link Sofa} и представляет диван в стиле модерн.
 * Экземпляры создаются через фабрику {@link ModernFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record ModernSofa() implements Sofa {
}
