package pattern3_behavior.behavior7_visitor.code.example3_shopping_cart;

/**
 * Конкретный элемент — продукт питания.
 *
 * <p>Хранит название, цену и вес в килограммах. В методе
 * {@link #accept(ProductVisitor)} вызывает
 * {@code visitor.visitFood(this)}.
 */
public class Food implements Product {

    /** Название продукта. */
    private final String name;

    /** Цена продукта. */
    private final double price;

    /** Вес продукта в килограммах. */
    private final double weightKg;

    /**
     * Создаёт продукт питания.
     *
     * @param name     название продукта
     * @param price    цена продукта
     * @param weightKg вес в килограммах
     */
    public Food(String name, double price, double weightKg) {
        this.name = name;
        this.price = price;
        this.weightKg = weightKg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    /**
     * @return вес продукта в килограммах
     */
    public double getWeightKg() {
        return weightKg;
    }

    /**
     * Принимает посетителя, направляя вызов в {@code visitFood}.
     *
     * @param visitor посетитель, выполняющий операцию
     */
    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visitFood(this);
    }
}
