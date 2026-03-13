package pattern3_behavior.behavior2_strategy.code.example3_discount;

/**
 * Заказ — контекст паттерна Стратегия (роль <b>Context</b>).
 *
 * <p>Хранит информацию о товаре (название и цену) и ссылку на текущую
 * стратегию скидки ({@link DiscountStrategy}). Контекст делегирует расчёт
 * скидки стратегии, не зная деталей конкретного алгоритма.
 *
 * <p><b>Стратегия по умолчанию:</b> если стратегия не указана явно,
 * используется {@link RegularDiscount} (без скидки) — подход Null-Object,
 * который избавляет от проверок на {@code null}.
 *
 * <p><b>Когда использовать:</b> когда итоговая цена заказа зависит от
 * статуса покупателя или промоакции, и логику расчёта скидки нужно
 * менять без изменения класса заказа.
 */
public class Order {

    /** Название товара. */
    private final String productName;

    /** Исходная цена товара (до скидки). */
    private final double price;

    /**
     * Текущая стратегия скидки.
     * По умолчанию — {@link RegularDiscount} (без скидки).
     */
    private DiscountStrategy discountStrategy;

    /**
     * Создаёт заказ с указанным товаром и ценой.
     * Стратегия скидки по умолчанию — {@link RegularDiscount}.
     *
     * @param productName название товара
     * @param price       исходная цена товара
     */
    public Order(String productName, double price) {
        this.productName = productName;
        this.price = price;
        this.discountStrategy = new RegularDiscount();
    }

    /**
     * Заменяет стратегию скидки на новую.
     *
     * @param discountStrategy новая стратегия скидки
     */
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * Рассчитывает итоговую цену с учётом текущей скидки
     * и выводит детали заказа в консоль.
     */
    public void checkout() {
        double discount = discountStrategy.calculateDiscount(price);
        double finalPrice = price - discount;
        System.out.println("Товар: " + productName);
        System.out.println("Цена: " + price + " ₽");
        System.out.println("Скидка: " + discount + " ₽");
        System.out.println("Итого: " + finalPrice + " ₽");
        System.out.println();
    }
}
