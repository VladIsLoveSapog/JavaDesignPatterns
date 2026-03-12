package pattern1_creation.create3_abstract_factory.code.modern;

import pattern1_creation.create3_abstract_factory.code.Table;

/**
 * [ConcreteProduct]
 * Стол в стиле модерн.
 *
 * <p>Создаётся только через {@link ModernFurnitureFactory} — не инстанциируй
 * напрямую в клиентском коде. Клиент получает этот объект под типом {@code Table}.
 */
public record ModernTable() implements Table {
}
