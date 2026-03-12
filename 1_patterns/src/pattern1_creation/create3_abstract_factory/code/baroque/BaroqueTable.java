package pattern1_creation.create3_abstract_factory.code.baroque;

import pattern1_creation.create3_abstract_factory.code.Table;

/**
 * [ConcreteProduct]
 * Стол в стиле барокко.
 *
 * <p>Создаётся только через {@link BaroqueFurnitureFactory} — не инстанциируй
 * напрямую в клиентском коде. Клиент получает этот объект под типом {@code Table}.
 */
public record BaroqueTable() implements Table {
}
