package pattern1_creation.create3_abstract_factory.code.gotik;

import pattern1_creation.create3_abstract_factory.code.Table;

/**
 * [ConcreteProduct]
 * Стол в стиле готика.
 *
 * <p>Создаётся только через {@link GoticFurnitureFactory} — не инстанциируй
 * напрямую в клиентском коде. Клиент получает этот объект под типом {@code Table}.
 */
public record GoticTable() implements Table {
}
