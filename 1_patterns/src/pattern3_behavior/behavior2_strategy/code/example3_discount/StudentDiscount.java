package pattern3_behavior.behavior2_strategy.code.example3_discount;

/**
 * Студенческая скидка — 15 % (роль <b>ConcreteStrategy</b>).
 *
 * <p>Реализует фиксированный процент скидки для студентов.
 * Алгоритм прост: {@code скидка = цена × 0.15}.
 *
 * <p><b>Когда использовать:</b> покупатель подтвердил студенческий статус
 * (например, через верификацию студенческого билета).
 */
public class StudentDiscount implements DiscountStrategy {

    /**
     * Рассчитывает студенческую скидку (15 % от цены).
     *
     * @param price исходная цена товара
     * @return сумма скидки (15 % от {@code price})
     */
    @Override
    public double calculateDiscount(double price) {
        return price * 0.15;
    }
}
