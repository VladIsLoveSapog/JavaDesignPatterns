package pattern2_structural.struct7_flyweight.code.example2_characters;

/**
 * Точка входа — демонстрация паттерна Легковес на примере текстового редактора.
 *
 * <p>Отображает строку {@code "HELLO HELLO"} посимвольно. Каждый символ
 * запрашивается у фабрики {@link CharacterFlyweightFactory}, которая возвращает
 * уже существующий легковес из кэша при повторении символа.
 *
 * <p>В результате для 11 символов строки создаётся всего 6 уникальных объектов
 * (H, E, L, O, пробел, — повторные 'L', 'O', 'H' и т.д. берутся из кэша).
 */
public class FlyweightPatternMain {
    public static void main(String[] args) {
        String text = "HELLO HELLO";
        String font = "Arial";
        int size = 12;

        int x = 0;
        int y = 0;

        for (char c : text.toCharArray()) {
            CharacterFlyweight flyweight = CharacterFlyweightFactory.getCharacter(c, font, size);
            CharacterContext context = new CharacterContext(x, y);
            flyweight.display(context);
            x += 10; // Увеличение позиции по оси X
        }

        System.out.println("Total Flyweight objects created: " + CharacterFlyweightFactory.getFlyweightCount());
    }
}
