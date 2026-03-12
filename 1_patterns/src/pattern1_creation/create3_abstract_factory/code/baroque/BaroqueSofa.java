package pattern1_creation.create3_abstract_factory.code.baroque;

import pattern1_creation.create3_abstract_factory.code.Sofa;

/**
 * [ConcreteProduct]
 * Диван в стиле барокко.
 *
 * <p>Создаётся только через {@link BaroqueFurnitureFactory} — не инстанциируй
 * напрямую в клиентском коде. Клиент получает этот объект под типом {@code Sofa}.
 */
public record BaroqueSofa() implements Sofa {
}
