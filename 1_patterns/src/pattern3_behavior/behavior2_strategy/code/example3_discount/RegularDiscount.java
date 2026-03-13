package pattern3_behavior.behavior2_strategy.code.example3_discount;

/**
 * Обычная покупка — без скидки (роль <b>ConcreteStrategy</b>).
 *
 * <p>Реализует подход <b>Null-Object</b>: вместо проверки на {@code null}
 * в контексте используется стратегия, которая всегда возвращает нулевую скидку.
 * Это упрощает код {@link Order} — ему не нужно проверять, установлена ли стратегия.
 *
 * <p><b>Когда использовать:</b> для обычных покупателей, у которых нет
 * ни промокода, ни статуса, дающего скидку.
 */
public class RegularDiscount implements DiscountStrategy {

    /**
     * Возвращает нулевую скидку — цена остаётся без изменений.
     *
     * @param price исходная цена товара
     * @return {@code 0.0} — скидка отсутствует
     */
    @Override
    public double calculateDiscount(double price) {
        return 0;
    }
}
