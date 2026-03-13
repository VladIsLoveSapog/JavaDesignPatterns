package pattern3_behavior.behavior1_chain.code.example5_discount;

/**
 * <p><b>Абстрактный базовый обработчик скидок — реализует общую логику передачи по цепочке.</b></p>
 *
 * <p>Содержит ссылку на следующий обработчик ({@code nextHandler}) и реализует метод
 * {@link #applyDiscount(Order)}, который:</p>
 * <ol>
 *     <li>Вызывает абстрактный метод {@link #calculateDiscount(Order)} для расчёта скидки.</li>
 *     <li><b>Всегда</b> передаёт запрос следующему обработчику, если он установлен.</li>
 * </ol>
 *
 * <p>Такой подход позволяет скидкам <b>накапливаться</b> — каждый узел цепочки
 * добавляет свою скидку независимо от остальных.</p>
 *
 * <p>Наследники должны реализовать метод {@link #calculateDiscount(Order)},
 * содержащий конкретную логику проверки условия и начисления скидки.</p>
 *
 * @see DiscountHandler
 * @see NewCustomerDiscount
 * @see BulkPurchaseDiscount
 * @see PromoCodeDiscount
 */
public abstract class BaseDiscountHandler implements DiscountHandler {

    /**
     * Ссылка на следующий обработчик в цепочке. Может быть {@code null},
     * если текущий обработчик является последним.
     */
    protected DiscountHandler nextHandler;

    /**
     * Устанавливает следующий обработчик в цепочке.
     *
     * @param next следующий обработчик скидок
     */
    @Override
    public void setNext(DiscountHandler next) {
        this.nextHandler = next;
    }

    /**
     * Применяет скидку к заказу и передаёт его следующему обработчику.
     *
     * <p>Сначала вызывается {@link #calculateDiscount(Order)} для выполнения
     * конкретной логики начисления скидки. Затем, если в цепочке есть следующий
     * обработчик, заказ передаётся ему.</p>
     *
     * @param order заказ, к которому применяется скидка
     */
    @Override
    public void applyDiscount(Order order) {
        calculateDiscount(order);
        if (nextHandler != null) {
            nextHandler.applyDiscount(order);
        }
    }

    /**
     * Абстрактный метод расчёта скидки — реализуется конкретными обработчиками.
     *
     * <p>Каждый наследник проверяет своё условие и, если оно выполнено,
     * добавляет скидку к заказу через {@link Order#addDiscount(double)}.</p>
     *
     * @param order заказ, для которого рассчитывается скидка
     */
    protected abstract void calculateDiscount(Order order);
}
