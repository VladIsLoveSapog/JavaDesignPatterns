package pattern1_creation.create3_abstract_factory.code.example1_furniture.baroque;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Chair;

/**
 * Конкретный продукт — кресло в стиле барокко.
 *
 * <p>Реализует интерфейс {@link Chair} и представляет кресло в стиле барокко.
 * Экземпляры создаются через фабрику {@link BaroqueFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record BaroqueChair() implements Chair {
}
