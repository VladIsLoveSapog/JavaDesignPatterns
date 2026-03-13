package pattern2_structural.struct7_flyweight.code.example2_characters;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика легковесов (Flyweight Factory) — управляет созданием и кэшированием символов.
 *
 * <p>Хранит ранее созданные экземпляры {@link ConcreteCharacterFlyweight} в кэше {@code flyweights}.
 * Ключ кэша формируется как {@code "символ-шрифт-размер"} (например, {@code "H-Arial-12"}).
 *
 * <p>При запросе символа через {@link #getCharacter(char, String, int)}:
 * <ul>
 *     <li>если объект с таким ключом уже существует — возвращается из кэша;</li>
 *     <li>если нет — создаётся новый, помещается в кэш и возвращается.</li>
 * </ul>
 */
public class CharacterFlyweightFactory {
    private static final Map<String, CharacterFlyweight> flyweights = new HashMap<>();

    /**
     * Возвращает легковес символа из кэша или создаёт новый, если такого ещё нет.
     *
     * @param character символ
     * @param font      шрифт
     * @param size      размер шрифта
     * @return экземпляр {@link CharacterFlyweight} из кэша
     */
    public static CharacterFlyweight getCharacter(char character, String font, int size) {
        String key = character + "-" + font + "-" + size;
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteCharacterFlyweight(character, font, size));
            System.out.println("Creating new Flyweight for: " + key);
        }
        return flyweights.get(key);
    }

    /**
     * Возвращает количество уникальных легковесов в кэше.
     *
     * @return количество созданных легковесов
     */
    public static int getFlyweightCount() {
        return flyweights.size();
    }
}
