package pattern2_structural.struct7_flyweight.code.example2_characters;

/**
 * Контекст (Context) — внешнее состояние символа в текстовом редакторе.
 *
 * <p>Хранит позицию символа на экране ({@code x}, {@code y}), которая уникальна
 * для каждого вхождения символа в документ. Это <b>внешнее (неразделяемое) состояние</b>,
 * которое передаётся легковесу ({@link CharacterFlyweight}) при отображении.
 *
 * <p>Разделение на внутреннее состояние (в {@link ConcreteCharacterFlyweight})
 * и внешнее состояние (в этом классе) позволяет использовать один объект символа
 * для множества позиций в тексте.
 */
public class CharacterContext {
    private final int x;
    private final int y;

    /**
     * Создаёт контекст с указанной позицией.
     *
     * @param x координата по горизонтали
     * @param y координата по вертикали
     */
    public CharacterContext(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает горизонтальную координату символа.
     *
     * @return координата x
     */
    public int getX() {
        return x;
    }

    /**
     * Возвращает вертикальную координату символа.
     *
     * @return координата y
     */
    public int getY() {
        return y;
    }
}
