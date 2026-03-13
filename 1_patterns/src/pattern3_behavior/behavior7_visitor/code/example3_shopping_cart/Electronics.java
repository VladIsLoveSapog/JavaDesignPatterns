package pattern3_behavior.behavior7_visitor.code.example3_shopping_cart;

/**
 * Конкретный элемент — электроника.
 *
 * <p>Хранит название, цену и срок гарантии. В методе
 * {@link #accept(ProductVisitor)} вызывает
 * {@code visitor.visitElectronics(this)}.
 */
public class Electronics implements Product {

    /** Название товара. */
    private final String name;

    /** Цена товара. */
    private final double price;

    /** Срок гарантии в месяцах. */
    private final int warrantyMonths;

    /**
     * Создаёт электронный товар.
     *
     * @param name           название товара
     * @param price          цена товара
     * @param warrantyMonths срок гарантии в месяцах
     */
    public Electronics(String name, double price, int warrantyMonths) {
        this.name = name;
        this.price = price;
        this.warrantyMonths = warrantyMonths;
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
     * @return срок гарантии в месяцах
     */
    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    /**
     * Принимает посетителя, направляя вызов в {@code visitElectronics}.
     *
     * @param visitor посетитель, выполняющий операцию
     */
    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visitElectronics(this);
    }
}
