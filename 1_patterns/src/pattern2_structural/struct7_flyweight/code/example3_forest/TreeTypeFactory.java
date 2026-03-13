package pattern2_structural.struct7_flyweight.code.example3_forest;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика легковесов (Flyweight Factory) — управляет типами деревьев.
 *
 * <p>Хранит ранее созданные экземпляры {@link TreeType} в кэше {@code treeTypes}.
 * Ключ кэша формируется как {@code "порода-цвет-текстура"}
 * (например, {@code "Берёза-Зелёный-Гладкая"}).
 *
 * <p>При запросе типа дерева через {@link #getTreeType(String, String, String)}:
 * <ul>
 *     <li>если объект с таким ключом уже существует — возвращается из кэша;</li>
 *     <li>если нет — создаётся новый, помещается в кэш и возвращается.</li>
 * </ul>
 */
public class TreeTypeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    /**
     * Возвращает тип дерева из кэша или создаёт новый, если такого ещё нет.
     *
     * @param species порода дерева
     * @param color   цвет листвы/хвои
     * @param texture текстура коры
     * @return экземпляр {@link TreeType} из кэша
     */
    public static TreeType getTreeType(String species, String color, String texture) {
        String key = species + "-" + color + "-" + texture;
        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new TreeType(species, color, texture));
            System.out.println("Creating new TreeType: " + key);
        }
        return treeTypes.get(key);
    }

    /**
     * Возвращает количество уникальных типов деревьев в кэше.
     *
     * @return количество типов деревьев
     */
    public static int getTypeCount() {
        return treeTypes.size();
    }
}
