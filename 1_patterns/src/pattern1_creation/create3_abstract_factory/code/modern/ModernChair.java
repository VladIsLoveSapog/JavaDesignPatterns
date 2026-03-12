package pattern1_creation.create3_abstract_factory.code.modern;

import pattern1_creation.create3_abstract_factory.code.Chair;

/**
 * [ConcreteProduct]
 * Кресло в стиле модерн.
 *
 * <p>Создаётся только через {@link ModernFurnitureFactory} — не инстанциируй
 * напрямую в клиентском коде. Клиент получает этот объект под типом {@code Chair}.
 */
public record ModernChair() implements Chair {}
