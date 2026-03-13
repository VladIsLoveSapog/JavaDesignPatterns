package pattern1_creation.create4_prototype.code.example4_prototype_registry;

import java.util.HashMap;
import java.util.Map;

/**
 * Реестр прототипов — хранит шаблонные объекты и создаёт копии по ключу.
 * Аналогичный механизм используется в Spring Framework (prototype scope):
 * контейнер хранит определение бина и при каждом запросе создаёт новый экземпляр.
 */
public class ShapeRegistry {
    private final Map<String, Shape> prototypes = new HashMap<>();

    public void register(String key, Shape shape) {
        prototypes.put(key, shape);
    }

    /**
     * Создаёт новый объект путём клонирования зарегистрированного прототипа.
     * @param key ключ прототипа
     * @return клон прототипа
     * @throws IllegalArgumentException если ключ не найден
     */
    public Shape create(String key) {
        Shape prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Прототип не найден: " + key);
        }
        return prototype.clone();
    }

    public boolean contains(String key) {
        return prototypes.containsKey(key);
    }
}
