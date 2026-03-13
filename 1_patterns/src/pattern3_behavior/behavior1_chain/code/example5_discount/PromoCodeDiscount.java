package pattern3_behavior.behavior1_chain.code.example5_discount;

/**
 * <p><b>Обработчик скидки по промокоду — начисляет 15% при вводе кода {@code "SALE2024"}.</b></p>
 *
 * <p>Проверяет значение {@link Order#getPromoCode()}. Если промокод равен
 * {@code "SALE2024"}, к заказу добавляется скидка {@code 15%} через метод
 * {@link Order#addDiscount(double)}. В противном случае скидка не начисляется.</p>
 *
 * <p>Независимо от результата проверки, заказ передаётся следующему обработчику
 * в цепочке (логика передачи реализована в {@link BaseDiscountHandler#applyDiscount(Order)}).</p>
 *
 * @see BaseDiscountHandler
 * @see DiscountHandler
 * @see Order
 */
public class PromoCodeDiscount extends BaseDiscountHandler {

    /**
     * Процент скидки по промокоду.
     */
    private static final double DISCOUNT_PERCENT = 15.0;

    /**
     * Действующий промокод для получения скидки.
     */
    private static final String VALID_PROMO_CODE = "SALE2024";

    /**
     * Рассчитывает скидку по промокоду.
     *
     * <p>Если {@link Order#getPromoCode()} равен {@code "SALE2024"},
     * добавляет {@code 15%} к накопленной скидке заказа.</p>
     *
     * @param order заказ, для которого проверяется условие скидки
     */
    @Override
    protected void calculateDiscount(Order order) {
        if (VALID_PROMO_CODE.equals(order.getPromoCode())) {
            order.addDiscount(DISCOUNT_PERCENT);
            System.out.println("[Скидка по промокоду]: применена скидка " + DISCOUNT_PERCENT
                    + "% — промокод \"" + VALID_PROMO_CODE + "\" активирован");
        } else {
            System.out.println("[Скидка по промокоду]: не применяется — промокод \""
                    + (order.getPromoCode() != null ? order.getPromoCode() : "нет") + "\" недействителен");
        }
    }
}
