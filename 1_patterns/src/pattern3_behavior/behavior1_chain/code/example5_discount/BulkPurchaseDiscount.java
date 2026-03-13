package pattern3_behavior.behavior1_chain.code.example5_discount;

/**
 * <p><b>Обработчик скидки за крупный заказ — начисляет 10% при сумме свыше 5000.</b></p>
 *
 * <p>Проверяет значение {@link Order#getAmount()}. Если сумма заказа превышает
 * {@code 5000}, к заказу добавляется скидка {@code 10%} через метод
 * {@link Order#addDiscount(double)}. В противном случае скидка не начисляется.</p>
 *
 * <p>Независимо от результата проверки, заказ передаётся следующему обработчику
 * в цепочке (логика передачи реализована в {@link BaseDiscountHandler#applyDiscount(Order)}).</p>
 *
 * @see BaseDiscountHandler
 * @see DiscountHandler
 * @see Order
 */
public class BulkPurchaseDiscount extends BaseDiscountHandler {

    /**
     * Процент скидки за крупный заказ.
     */
    private static final double DISCOUNT_PERCENT = 10.0;

    /**
     * Минимальная сумма заказа для получения скидки.
     */
    private static final double MIN_AMOUNT = 5000.0;

    /**
     * Рассчитывает скидку за крупный заказ.
     *
     * <p>Если {@link Order#getAmount()} превышает {@code 5000},
     * добавляет {@code 10%} к накопленной скидке заказа.</p>
     *
     * @param order заказ, для которого проверяется условие скидки
     */
    @Override
    protected void calculateDiscount(Order order) {
        if (order.getAmount() > MIN_AMOUNT) {
            order.addDiscount(DISCOUNT_PERCENT);
            System.out.println("[Скидка за крупный заказ]: применена скидка " + DISCOUNT_PERCENT
                    + "% к заказу на сумму " + order.getAmount());
        } else {
            System.out.println("[Скидка за крупный заказ]: не применяется — сумма заказа "
                    + order.getAmount() + " не превышает " + MIN_AMOUNT);
        }
    }
}
