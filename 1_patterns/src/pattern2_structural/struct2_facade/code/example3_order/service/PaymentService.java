package pattern2_structural.struct2_facade.code.example3_order.service;

/**
 * Сервис обработки платежей.
 * <p>
 * Выполняет списание и возврат средств.
 * Является частью подсистемы оформления заказа, скрытой за фасадом
 * {@link pattern2_structural.struct2_facade.code.example3_order.OrderFacade}.
 */
public class PaymentService {

    /**
     * Обрабатывает платёж по заказу.
     *
     * @param orderId идентификатор заказа
     * @param amount  сумма платежа
     * @return {@code true}, если платёж успешен
     */
    public boolean processPayment(String orderId, double amount) {
        System.out.println("[Оплата] Списание " + amount + " руб. по заказу " + orderId);
        return true;
    }

    /**
     * Выполняет возврат средств (компенсация при ошибке).
     *
     * @param orderId идентификатор заказа
     * @param amount  сумма возврата
     */
    public void refund(String orderId, double amount) {
        System.out.println("[Оплата] Возврат " + amount + " руб. по заказу " + orderId);
    }
}
