package pattern1_creation.create2_factory_method.code.example2_static_method;

/**
 * Продукт — напиток, создаваемый через статические фабричные методы.
 *
 * Аналог подхода {@code List.of()}, {@code Optional.of()} из стандартной библиотеки:
 * класс сам знает, как создавать свои экземпляры, и предоставляет
 * именованные статические методы вместо публичного конструктора.
 *
 * Использование: Drink.createCoffee(200, "Эспрессо")
 *               Drink.createTea("Зелёный")
 */
public class Drink {

    /** Название напитка */
    private final String name;

    /** Объём напитка в миллилитрах */
    private final int volumeMl;

    /** Тип напитка (кофе, чай и т.д.) */
    private final String type;

    /**
     * Приватный конструктор — создание только через статические фабричные методы.
     *
     * @param name     название напитка
     * @param volumeMl объём в мл
     * @param type     тип напитка
     */
    private Drink(String name, int volumeMl, String type) {
        this.name = name;
        this.volumeMl = volumeMl;
        this.type = type;
    }

    // =========================================================================
    // Статические фабричные методы
    // =========================================================================

    /**
     * Создать кофе.
     *
     * @param volumeMl   объём порции в мл (например, 200 или 400)
     * @param coffeeType вид кофе (например, "Эспрессо", "Капучино")
     * @return готовый объект Drink с типом «кофе»
     */
    public static Drink createCoffee(int volumeMl, String coffeeType) {
        System.out.println("[Фабрика] Готовим кофе: " + coffeeType + " (" + volumeMl + " мл)");
        return new Drink("Кофе (" + coffeeType + ")", volumeMl, "coffee");
    }

    /**
     * Создать чай.
     *
     * @param teaSort сорт чая (например, "Зелёный", "Чёрный")
     * @return готовый объект Drink с типом «чай»
     */
    public static Drink createTea(String teaSort) {
        System.out.println("[Фабрика] Завариваем чай: " + teaSort);
        return new Drink("Чай (" + teaSort + ")", 250, "tea");
    }

    /**
     * Описать напиток.
     */
    public void describe() {
        switch (type) {
            case "coffee":
                System.out.println("☕ " + name + ", объём: " + volumeMl + " мл. "
                        + "Аромат свежемолотого кофе!");
                break;
            case "tea":
                System.out.println("🍵 " + name + ", объём: " + volumeMl + " мл. "
                        + "Чай заварен идеально!");
                break;
            default:
                System.out.println("🥤 " + name + ", объём: " + volumeMl + " мл.");
        }
    }

    public String getName() {
        return name;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public String getType() {
        return type;
    }
}
