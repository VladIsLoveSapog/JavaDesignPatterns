package pattern3_behavior.behavior7_visitor.code.example3_shopping_cart;

/**
 * Конкретный элемент — книга.
 *
 * <p>Хранит название и цену. В методе {@link #accept(ProductVisitor)}
 * вызывает {@code visitor.visitBook(this)}, обеспечивая двойную
 * диспетчеризацию.
 */
public class Book implements Product {

    /** Название книги. */
    private final String name;

    /** Цена книги. */
    private final double price;

    /**
     * Создаёт книгу с заданным названием и ценой.
     *
     * @param name  название книги
     * @param price цена книги
     */
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
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
     * Принимает посетителя, направляя вызов в {@code visitBook}.
     *
     * @param visitor посетитель, выполняющий операцию
     */
    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visitBook(this);
    }
}
