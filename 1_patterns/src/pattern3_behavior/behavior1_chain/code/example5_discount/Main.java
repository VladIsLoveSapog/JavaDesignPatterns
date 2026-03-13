package pattern3_behavior.behavior1_chain.code.example5_discount;

/**
 * <p><b>Демонстрация паттерна «Цепочка обязанностей» на примере расчёта скидок
 * в интернет-магазине.</b></p>
 *
 * <p>В данном примере цепочка обработчиков скидок последовательно проверяет
 * условия и <b>накапливает</b> скидки. Каждый обработчик независимо решает,
 * начислять ли свою скидку, и <b>всегда</b> передаёт заказ дальше по цепочке.</p>
 *
 * <p>Цепочка обработчиков:</p>
 * <ol>
 *     <li>{@link NewCustomerDiscount} — 5% для новых покупателей</li>
 *     <li>{@link BulkPurchaseDiscount} — 10% при сумме заказа свыше 5000</li>
 *     <li>{@link PromoCodeDiscount} — 15% по промокоду {@code "SALE2024"}</li>
 * </ol>
 *
 * <p>Тесты демонстрируют три сценария с разными комбинациями скидок.</p>
 *
 * @see Order
 * @see DiscountHandler
 * @see BaseDiscountHandler
 */
public class Main {

    public static void main(String[] args) {

        // Создаём обработчики скидок
        DiscountHandler newCustomerDiscount = new NewCustomerDiscount();
        DiscountHandler bulkPurchaseDiscount = new BulkPurchaseDiscount();
        DiscountHandler promoCodeDiscount = new PromoCodeDiscount();

        // Связываем обработчики в цепочку
        newCustomerDiscount.setNext(bulkPurchaseDiscount);
        bulkPurchaseDiscount.setNext(promoCodeDiscount);

        // =====================================================================
        // Тест 1: Новый клиент, сумма 3000, без промокода
        // Ожидается: только скидка для нового клиента (5%)
        // =====================================================================
        System.out.println("========== Тест 1: Новый клиент, сумма 3000, без промокода ==========");
        Order order1 = new Order("NEW", 3000, null);
        newCustomerDiscount.applyDiscount(order1);
        System.out.println("Результат: " + order1);

        System.out.println();
        System.out.println("=====================================================================");
        System.out.println();

        // =====================================================================
        // Тест 2: Постоянный клиент, сумма 7000, промокод "SALE2024"
        // Ожидается: скидка за крупный заказ (10%) + скидка по промокоду (15%) = 25%
        // =====================================================================
        System.out.println("========== Тест 2: Постоянный клиент, сумма 7000, промокод SALE2024 ==========");
        Order order2 = new Order("REGULAR", 7000, "SALE2024");
        newCustomerDiscount.applyDiscount(order2);
        System.out.println("Результат: " + order2);

        System.out.println();
        System.out.println("=====================================================================");
        System.out.println();

        // =====================================================================
        // Тест 3: Новый клиент, сумма 8000, промокод "SALE2024"
        // Ожидается: все три скидки (5% + 10% + 15% = 30%)
        // =====================================================================
        System.out.println("========== Тест 3: Новый клиент, сумма 8000, промокод SALE2024 ==========");
        Order order3 = new Order("NEW", 8000, "SALE2024");
        newCustomerDiscount.applyDiscount(order3);
        System.out.println("Результат: " + order3);
    }
}
