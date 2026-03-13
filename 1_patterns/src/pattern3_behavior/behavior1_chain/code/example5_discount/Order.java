package pattern3_behavior.behavior1_chain.code.example5_discount;

/**
 * <p><b>Класс заказа — объект-запрос, проходящий по цепочке обработчиков скидок.</b></p>
 *
 * <p>Содержит информацию о покупателе, сумме заказа и промокоде.
 * Поле {@code totalDiscount} накапливает суммарную скидку в процентах,
 * которая увеличивается каждым обработчиком в цепочке, если условие выполнено.</p>
 *
 * <p>Используется в связке с {@link DiscountHandler} и его реализациями:
 * {@link NewCustomerDiscount}, {@link BulkPurchaseDiscount}, {@link PromoCodeDiscount}.</p>
 *
 * @see DiscountHandler
 * @see BaseDiscountHandler
 */
public class Order {

    /**
     * Тип покупателя: {@code "NEW"} — новый, {@code "REGULAR"} — постоянный.
     */
    private final String customerType;

    /**
     * Сумма заказа в условных единицах.
     */
    private final double amount;

    /**
     * Промокод, введённый покупателем. Может быть {@code null}, если промокод не указан.
     */
    private final String promoCode;

    /**
     * Накопленная скидка в процентах. Начальное значение — {@code 0}.
     * Увеличивается методом {@link #addDiscount(double)}.
     */
    private double totalDiscount;

    /**
     * Создаёт новый заказ с указанными параметрами.
     *
     * @param customerType тип покупателя ({@code "NEW"} или {@code "REGULAR"})
     * @param amount       сумма заказа
     * @param promoCode    промокод (может быть {@code null})
     */
    public Order(String customerType, double amount, String promoCode) {
        this.customerType = customerType;
        this.amount = amount;
        this.promoCode = promoCode;
        this.totalDiscount = 0;
    }

    /**
     * Возвращает тип покупателя.
     *
     * @return строка {@code "NEW"} или {@code "REGULAR"}
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Возвращает сумму заказа.
     *
     * @return сумма заказа
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Возвращает промокод, указанный покупателем.
     *
     * @return промокод или {@code null}, если не указан
     */
    public String getPromoCode() {
        return promoCode;
    }

    /**
     * Возвращает текущую накопленную скидку в процентах.
     *
     * @return суммарная скидка в процентах
     */
    public double getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Добавляет указанный процент скидки к уже накопленной сумме.
     *
     * <p>Например, если текущая скидка {@code 5%} и вызывается {@code addDiscount(10)},
     * итоговая скидка станет {@code 15%}.</p>
     *
     * @param discount процент скидки, который необходимо добавить
     */
    public void addDiscount(double discount) {
        this.totalDiscount += discount;
    }

    /**
     * Возвращает строковое представление заказа с информацией о покупателе,
     * сумме, промокоде и накопленной скидке.
     *
     * @return строковое описание заказа
     */
    @Override
    public String toString() {
        return "Заказ{" +
                "тип клиента='" + customerType + '\'' +
                ", сумма=" + amount +
                ", промокод='" + (promoCode != null ? promoCode : "нет") + '\'' +
                ", итоговая скидка=" + totalDiscount + "%" +
                '}';
    }
}
