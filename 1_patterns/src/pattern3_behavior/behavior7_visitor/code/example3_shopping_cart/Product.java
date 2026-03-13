package pattern3_behavior.behavior7_visitor.code.example3_shopping_cart;

/**
 * Интерфейс товара (Element в паттерне Visitor).
 *
 * <p>Каждый тип товара ({@link Book}, {@link Electronics}, {@link Food})
 * реализует этот интерфейс, предоставляя метод {@link #accept(ProductVisitor)}
 * для приёма посетителя.
 *
 * <p><b>Зачем нужен accept:</b> метод обеспечивает двойную диспетчеризацию —
 * конкретный товар сам вызывает нужный метод посетителя, передавая {@code this}.
 * Это позволяет посетителю выполнять разную логику для разных типов товаров
 * без {@code instanceof} и приведения типов.
 */
public interface Product {

    /**
     * @return название товара
     */
    String getName();

    /**
     * @return цена товара
     */
    double getPrice();

    /**
     * Принимает посетителя и делегирует ему выполнение операции.
     *
     * @param visitor посетитель, выполняющий операцию над товаром
     */
    void accept(ProductVisitor visitor);
}
