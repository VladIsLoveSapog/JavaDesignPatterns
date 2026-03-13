package pattern3_behavior.behavior1_chain.code.example5_discount;

/**
 * <p><b>Обработчик скидки для новых покупателей — начисляет 5% скидку.</b></p>
 *
 * <p>Проверяет значение {@link Order#getCustomerType()}. Если тип покупателя
 * равен {@code "NEW"}, к заказу добавляется скидка {@code 5%} через метод
 * {@link Order#addDiscount(double)}. В противном случае скидка не начисляется.</p>
 *
 * <p>Независимо от результата проверки, заказ передаётся следующему обработчику
 * в цепочке (логика передачи реализована в {@link BaseDiscountHandler#applyDiscount(Order)}).</p>
 *
 * @see BaseDiscountHandler
 * @see DiscountHandler
 * @see Order
 */
public class NewCustomerDiscount extends BaseDiscountHandler {

    /**
     * Процент скидки для новых покупателей.
     */
    private static final double DISCOUNT_PERCENT = 5.0;

    /**
     * Рассчитывает скидку для нового покупателя.
     *
     * <p>Если {@link Order#getCustomerType()} равен {@code "NEW"},
     * добавляет {@code 5%} к накопленной скидке заказа.</p>
     *
     * @param order заказ, для которого проверяется условие скидки
     */
    @Override
    protected void calculateDiscount(Order order) {
        if ("NEW".equals(order.getCustomerType())) {
            order.addDiscount(DISCOUNT_PERCENT);
            System.out.println("[Скидка для нового клиента]: применена скидка " + DISCOUNT_PERCENT
                    + "% к заказу на сумму " + order.getAmount());
        } else {
            System.out.println("[Скидка для нового клиента]: не применяется — клиент не является новым");
        }
    }
}
