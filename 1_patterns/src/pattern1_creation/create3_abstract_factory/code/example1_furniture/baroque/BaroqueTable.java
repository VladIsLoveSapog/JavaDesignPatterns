package pattern1_creation.create3_abstract_factory.code.example1_furniture.baroque;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Table;

/**
 * Конкретный продукт — стол в стиле барокко.
 *
 * <p>Реализует интерфейс {@link Table} и представляет стол в стиле барокко.
 * Экземпляры создаются через фабрику {@link BaroqueFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record BaroqueTable() implements Table {
}
