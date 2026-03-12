package pattern1_creation.create3_abstract_factory.code.baroque;

import pattern1_creation.create3_abstract_factory.code.Chair;

/**
 * [ConcreteProduct]
 * Кресло в стиле барокко.
 *
 * <p>Создаётся только через {@link BaroqueFurnitureFactory} — не инстанциируй
 * напрямую в клиентском коде. Клиент получает этот объект под типом {@code Chair}.
 */
public record BaroqueChair() implements Chair {
}
