package pattern2_structural.struct7_flyweight.code.example2_characters;

/**
 * Конкретный легковес (Concrete Flyweight) — символ текстового редактора.
 *
 * <p>Хранит <b>внутреннее (разделяемое) состояние</b>:
 * <ul>
 *     <li>{@code character} — символ (например, 'H', 'E', 'L')</li>
 *     <li>{@code font} — шрифт (например, "Arial")</li>
 *     <li>{@code size} — размер шрифта (например, 12)</li>
 * </ul>
 * Все поля объявлены как {@code final} и неизменяемы после создания объекта.
 *
 * <p>Метод {@link #display(CharacterContext)} объединяет внутреннее состояние
 * (символ, шрифт, размер) с внешним (позиция x, y из {@link CharacterContext})
 * для вывода полной информации о символе.
 */
public class ConcreteCharacterFlyweight implements CharacterFlyweight {
    private final char character;
    private final String font;
    private final int size;

    /**
     * Создаёт легковес символа с указанными параметрами.
     *
     * @param character символ (внутреннее состояние)
     * @param font      шрифт (внутреннее состояние)
     * @param size      размер шрифта (внутреннее состояние)
     */
    public ConcreteCharacterFlyweight(char character, String font, int size) {
        this.character = character;
        this.font = font;
        this.size = size;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Выводит символ, шрифт, размер (внутреннее состояние) и позицию (внешнее состояние).
     */
    @Override
    public void display(CharacterContext context) {
        System.out.println("Character: " + character + ", Font: " + font + ", Size: " + size +
                ", Position: (" + context.getX() + ", " + context.getY() + ")");
    }
}
