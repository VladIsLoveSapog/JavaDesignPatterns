package pattern1_creation.create3_abstract_factory.code.example1_furniture.gotik;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.Sofa;

/**
 * Конкретный продукт — диван в стиле готика.
 *
 * <p>Реализует интерфейс {@link Sofa} и представляет диван в стиле готика.
 * Экземпляры создаются через фабрику {@link GoticFurnitureFactory},
 * что гарантирует согласованность семейства продуктов одного стиля.</p>
 */
public record GoticSofa() implements Sofa {
}
