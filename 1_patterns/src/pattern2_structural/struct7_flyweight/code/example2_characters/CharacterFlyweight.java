package pattern2_structural.struct7_flyweight.code.example2_characters;

/**
 * Паттерн Легковес (Flyweight) — интерфейс легковеса для символа текстового редактора.
 *
 * <p>Определяет метод {@link #display(CharacterContext)}, через который
 * конкретные легковесы ({@link ConcreteCharacterFlyweight}) отображают символ.
 *
 * <p>Внешнее состояние (позиция символа на экране) передаётся через параметр
 * {@link CharacterContext}, что позволяет одному и тому же объекту символа
 * отображаться в разных позициях документа.
 */
public interface CharacterFlyweight {
    /**
     * Отображает символ с учётом переданного контекста (внешнего состояния).
     *
     * @param context контекст, содержащий позицию символа (x, y) — внешнее состояние
     */
    void display(CharacterContext context);
}
