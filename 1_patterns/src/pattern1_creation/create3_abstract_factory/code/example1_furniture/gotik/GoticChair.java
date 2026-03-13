package pattern1_creation.create3_abstract_factory.code.example1_furniture.gotik;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Chair;

/**
 * Конкретный продукт — кресло в стиле готика.
 *
 * <p>Реализует интерфейс {@link Chair} и представляет кресло в стиле готика.
 * Экземпляры создаются через фабрику {@link GoticFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record GoticChair() implements Chair {
}
