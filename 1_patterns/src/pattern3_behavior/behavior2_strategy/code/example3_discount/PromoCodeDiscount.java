package pattern3_behavior.behavior2_strategy.code.example3_discount;

/**
 * Скидка по промокоду — фиксированная сумма (роль <b>ConcreteStrategy</b>).
 *
 * <p>В отличие от процентных стратегий, эта стратегия хранит <b>состояние</b> —
 * фиксированную сумму скидки, которая передаётся через конструктор.
 * Это показывает, что конкретная стратегия может иметь собственные параметры,
 * не влияя на общий интерфейс {@link DiscountStrategy}.
 *
 * <p>Если сумма промокода превышает цену товара, скидка ограничивается ценой
 * (покупатель не может получить «отрицательную» стоимость).
 *
 * <p><b>Когда использовать:</b> покупатель ввёл промокод с фиксированной
 * скидкой (например, «СКИДКА500» — 500 ₽ от любой покупки).
 */
public class PromoCodeDiscount implements DiscountStrategy {

    /** Фиксированная сумма скидки по промокоду. */
    private final double discountAmount;

    /**
     * Создаёт стратегию промокода с указанной суммой скидки.
     *
     * @param discountAmount фиксированная сумма скидки (≥ 0)
     */
    public PromoCodeDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Возвращает фиксированную скидку, но не более цены товара.
     *
     * @param price исходная цена товара
     * @return сумма скидки (не превышает {@code price})
     */
    @Override
    public double calculateDiscount(double price) {
        return Math.min(discountAmount, price);
    }
}
