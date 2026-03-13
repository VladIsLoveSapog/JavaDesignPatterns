package pattern3_behavior.behavior2_strategy.code.example3_discount;

/**
 * Демонстрация паттерна Стратегия на примере скидок в интернет-магазине.
 *
 * <p>Создаём {@link Order} и последовательно переключаем стратегии скидок
 * (без скидки → студенческая → промокод → VIP), наблюдая разную итоговую
 * цену для одного и того же товара.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Скидки: стратегии расчёта скидок ===\n");

        Order order = new Order("Наушники", 5000);

        // Обычная покупка — без скидки
        order.checkout();

        // Студенческая скидка — 15 %
        order.setDiscountStrategy(new StudentDiscount());
        order.checkout();

        // Промокод «СКИДКА500» — фиксированная скидка 500 ₽
        order.setDiscountStrategy(new PromoCodeDiscount(500));
        order.checkout();

        // VIP-скидка — 25 %
        order.setDiscountStrategy(new VipDiscount());
        order.checkout();
    }
}
