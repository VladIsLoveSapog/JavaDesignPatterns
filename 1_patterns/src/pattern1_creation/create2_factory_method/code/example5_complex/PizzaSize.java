package pattern1_creation.create2_factory_method.code.example5_complex;

/**
 * Перечисление размеров пиццы.
 * <p>
 * Используется фабрикой PizzaFactory для определения цены и порции.
 * Enum — удобный способ задать фиксированный набор значений.
 */
public enum PizzaSize {

    /**
     * Маленькая пицца (25 см)
     */
    SMALL("Маленькая", 25),

    /**
     * Средняя пицца (30 см)
     */
    MEDIUM("Средняя", 30),

    /**
     * Большая пицца (35 см)
     */
    LARGE("Большая", 35);

    /**
     * Человекочитаемое название размера
     */
    private final String displayName;

    /**
     * Диаметр в сантиметрах
     */
    private final int diameterCm;

    /**
     * Конструктор значения enum.
     *
     * @param displayName название для отображения
     * @param diameterCm  диаметр в см
     */
    PizzaSize(String displayName, int diameterCm) {
        this.displayName = displayName;
        this.diameterCm = diameterCm;
    }

    /**
     * Вернуть название размера для отображения.
     *
     * @return название (например, "Большая")
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Вернуть диаметр пиццы в сантиметрах.
     *
     * @return диаметр в см
     */
    public int getDiameterCm() {
        return diameterCm;
    }
}
