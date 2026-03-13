package pattern1_creation.create3_abstract_factory.code.example1_furniture.modern;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Chair;

/**
 * Конкретный продукт — кресло в стиле модерн.
 *
 * <p>Реализует интерфейс {@link Chair} и представляет кресло в стиле модерн.
 * Экземпляры создаются через фабрику {@link ModernFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record ModernChair() implements Chair {
}
