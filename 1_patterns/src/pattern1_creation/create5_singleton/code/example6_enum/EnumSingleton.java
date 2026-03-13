package pattern1_creation.create5_singleton.code.example6_enum;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum Singleton (Синглтон через перечисление).
 *
 * <p>Рекомендация Джошуа Блоха (Effective Java, Item 3):
 * «Enum — лучший способ реализации Singleton в Java».
 *
 * <p><b>Почему enum особенный:</b>
 * <ul>
 *   <li><b>Потокобезопасность</b> — JVM создаёт экземпляры enum при загрузке
 *       класса, аналогично Eager Initialization, но с гарантиями JLS.</li>
 *   <li><b>Защита от рефлексии</b> — {@code Constructor.newInstance()} для enum
 *       выбрасывает {@code IllegalArgumentException}. Невозможно создать
 *       второй экземпляр через рефлексию.</li>
 *   <li><b>Защита от сериализации</b> — механизм десериализации enum в Java
 *       всегда возвращает существующий экземпляр, не создавая новый.</li>
 * </ul>
 *
 * <p><b>Пример ниже:</b> реалистичный случай — хранение конфигурации приложения.
 * Конфигурация загружается один раз и доступна глобально.
 *
 * <p><b>Когда использовать:</b> когда нужна максимальная защита от нарушения
 * контракта синглтона (рефлексия, сериализация, клонирование).
 */
public enum EnumSingleton {

    /** Единственный экземпляр. */
    INSTANCE;

    /* Хранилище пар "ключ — значение" для конфигурации. */
    private final Map<String, String> config = new HashMap<>();

    /**
     * Устанавливает значение конфигурации.
     *
     * @param key   ключ параметра
     * @param value значение параметра
     */
    public void setProperty(String key, String value) {
        config.put(key, value);
    }

    /**
     * Возвращает значение конфигурации по ключу.
     *
     * @param key ключ параметра
     * @return значение или {@code null}, если ключ отсутствует
     */
    public String getProperty(String key) {
        return config.get(key);
    }

    /**
     * Демонстрационный метод.
     */
    public void printConfig() {
        System.out.println("Текущая конфигурация: " + config);
    }
}
