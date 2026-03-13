package pattern3_behavior.behavior7_visitor.code.example3_shopping_cart;

/**
 * Интерфейс посетителя товаров (Visitor в паттерне Visitor).
 *
 * <p>Объявляет метод посещения для каждого типа товара в корзине.
 * Конкретные посетители ({@link TaxCalculatorVisitor}, {@link DiscountVisitor})
 * реализуют эти методы, определяя свою операцию для каждого типа.
 *
 * <p><b>Сила паттерна:</b> чтобы добавить новую операцию (например, расчёт
 * стоимости доставки), достаточно создать нового посетителя —
 * классы товаров изменять не нужно.
 */
public interface ProductVisitor {

    /**
     * Выполняет операцию над книгой.
     *
     * @param book книга для обработки
     */
    void visitBook(Book book);

    /**
     * Выполняет операцию над электроникой.
     *
     * @param electronics электроника для обработки
     */
    void visitElectronics(Electronics electronics);

    /**
     * Выполняет операцию над продуктом питания.
     *
     * @param food продукт питания для обработки
     */
    void visitFood(Food food);
}
