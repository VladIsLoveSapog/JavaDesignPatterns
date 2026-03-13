package pattern2_structural.struct7_flyweight.code.example1_menu;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика легковесов (Flyweight Factory) — меню ресторана.
 *
 * <p>Хранит кэш ранее созданных легковесов в поле {@code menu} ({@link Map}).
 * При запросе позиции через метод {@link #getPosition(String)} возвращает
 * уже существующий экземпляр из кэша, вместо создания нового объекта.
 *
 * <p>Благодаря этому несколько заказов одной и той же пиццы или салата
 * ссылаются на один и тот же объект в памяти.
 */
public class MenuFactory {
    /** Кэш легковесов: ключ — название блюда, значение — экземпляр {@link MenuEntry}. */
    private final Map<String, MenuEntry> menu = new HashMap<>();

    public MenuFactory() {
        menu.put("Pizza Hawaii", new Pizza("Pizza Hawaii"));
        menu.put("Pizza Funghi", new Pizza("Pizza Funghi"));
        menu.put("Pizza Carbonara", new Pizza("Pizza Carbonara"));
        menu.put("Pizza Calzone", new Pizza("Pizza Calzone"));
        menu.put("Salad Caesar", new Salad("Salad Caesar"));
        menu.put("Salad Greek", new Salad("Salad Greek"));
    }

    /**
     * Возвращает позицию меню из кэша по названию.
     *
     * @param position название блюда
     * @return экземпляр {@link MenuEntry} из кэша, или {@code null}, если позиция не найдена
     */
    public MenuEntry getPosition(String position) {
        return menu.get(position);
    }
}
