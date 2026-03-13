package pattern1_creation.create3_abstract_factory.code.example1_furniture.gotik;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Table;

/**
 * Конкретный продукт — стол в стиле готика.
 *
 * <p>Реализует интерфейс {@link Table} и представляет стол в стиле готика.
 * Экземпляры создаются через фабрику {@link GoticFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record GoticTable() implements Table {
}
