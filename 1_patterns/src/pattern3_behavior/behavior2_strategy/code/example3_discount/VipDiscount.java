package pattern3_behavior.behavior2_strategy.code.example3_discount;

/**
 * VIP-скидка — 25 % (роль <b>ConcreteStrategy</b>).
 *
 * <p>Реализует максимальный фиксированный процент скидки для VIP-клиентов.
 * Алгоритм: {@code скидка = цена × 0.25}.
 *
 * <p><b>Когда использовать:</b> покупатель имеет VIP-статус
 * (например, по накопительной программе лояльности).
 */
public class VipDiscount implements DiscountStrategy {

    /**
     * Рассчитывает VIP-скидку (25 % от цены).
     *
     * @param price исходная цена товара
     * @return сумма скидки (25 % от {@code price})
     */
    @Override
    public double calculateDiscount(double price) {
        return price * 0.25;
    }
}
