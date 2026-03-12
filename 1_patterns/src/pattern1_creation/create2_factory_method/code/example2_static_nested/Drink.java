package pattern1_creation.create2_factory_method.code.example2_static_nested;

/**
 * Абстрактный продукт — напиток.
 *
 * Особенность этого варианта: фабрика реализована как вложенный статический класс
 * внутри самого продукта. Конкретные реализации напитков тоже вложены сюда.
 *
 * Использование: Drink.Factory.createCoffee(300)
 *               Drink.Factory.createTea("Зелёный")
 *
 * Плюс такого подхода: всё связанное с напитками находится в одном файле,
 * не загрязняя пространство имён лишними классами.
 */
public abstract class Drink {

    /** Название напитка */
    private final String name;

    /** Объём напитка в миллилитрах */
    private final int volumeMl;

    /**
     * Защищённый конструктор — создание только через фабрику или подклассы.
     *
     * @param name     название напитка
     * @param volumeMl объём в мл
     */
    protected Drink(String name, int volumeMl) {
        this.name = name;
        this.volumeMl = volumeMl;
    }

    /**
     * Описать напиток — каждый подкласс реализует по-своему.
     */
    public abstract void describe();

    /**
     * Вернуть название напитка.
     *
     * @return название
     */
    public String getName() {
        return name;
    }

    /**
     * Вернуть объём напитка.
     *
     * @return объём в мл
     */
    public int getVolumeMl() {
        return volumeMl;
    }

    // =========================================================================
    // Конкретные продукты — вложенные классы
    // =========================================================================

    /**
     * Конкретный продукт — кофе.
     * Вложен в Drink, чтобы логически группировать связанные классы.
     */
    public static class Coffee extends Drink {

        /** Вид кофе (эспрессо, капучино и т.д.) */
        private final String coffeeType;

        /**
         * Конструктор кофе.
         *
         * @param volumeMl   объём порции в мл
         * @param coffeeType вид кофе
         */
        public Coffee(int volumeMl, String coffeeType) {
            super("Кофе (" + coffeeType + ")", volumeMl);
            this.coffeeType = coffeeType;
        }

        /**
         * Описывает кофе: тип и объём.
         */
        @Override
        public void describe() {
            System.out.println("☕ " + getName() + ", объём: " + getVolumeMl() + " мл. "
                    + "Аромат свежемолотого кофе " + coffeeType + "!");
        }
    }

    /**
     * Конкретный продукт — чай.
     * Тоже вложен в Drink.
     */
    public static class Tea extends Drink {

        /** Сорт чая */
        private final String teaSort;

        /**
         * Конструктор чая.
         *
         * @param teaSort сорт чая (зелёный, чёрный и т.д.)
         */
        public Tea(String teaSort) {
            super("Чай (" + teaSort + ")", 250); // стандартная кружка чая — 250 мл
            this.teaSort = teaSort;
        }

        /**
         * Описывает чай: сорт и объём.
         */
        @Override
        public void describe() {
            System.out.println("🍵 " + getName() + ", объём: " + getVolumeMl() + " мл. "
                    + teaSort + " чай заварен идеально!");
        }
    }

    // =========================================================================
    // Вложенная статическая фабрика
    // =========================================================================

    /**
     * Вложенный статический класс-фабрика.
     *
     * Содержит статические фабричные методы для создания напитков.
     * Доступ: Drink.Factory.createCoffee(...), Drink.Factory.createTea(...)
     *
     * Такой подход удобен, когда хочется держать фабрику «рядом» с продуктом.
     */
    public static class Factory {

        /**
         * Приватный конструктор — класс не предназначен для создания объектов,
         * все методы статические.
         */
        private Factory() {
            // Утилитный класс — не создаём экземпляры
        }

        /**
         * Фабричный метод: создать кофе.
         *
         * @param volumeMl   объём порции в мл (например, 200 или 400)
         * @param coffeeType вид кофе (например, "Эспрессо", "Капучино")
         * @return готовый объект Coffee
         */
        public static Drink createCoffee(int volumeMl, String coffeeType) {
            System.out.println("[Фабрика] Готовим кофе: " + coffeeType + " (" + volumeMl + " мл)");
            return new Coffee(volumeMl, coffeeType);
        }

        /**
         * Фабричный метод: создать чай.
         *
         * @param teaSort сорт чая (например, "Зелёный", "Чёрный")
         * @return готовый объект Tea
         */
        public static Drink createTea(String teaSort) {
            System.out.println("[Фабрика] Завариваем чай: " + teaSort);
            return new Tea(teaSort);
        }
    }
}
