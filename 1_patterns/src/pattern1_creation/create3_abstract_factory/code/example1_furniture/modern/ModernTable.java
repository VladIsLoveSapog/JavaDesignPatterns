package pattern1_creation.create3_abstract_factory.code.example1_furniture.modern;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Table;

/**
 * Конкретный продукт — стол в стиле модерн.
 *
 * <p>Реализует интерфейс {@link Table} и представляет стол в стиле модерн.
 * Экземпляры создаются через фабрику {@link ModernFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record ModernTable() implements Table {
}
