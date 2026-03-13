package pattern2_structural.struct2_facade.code.example3_order.service;

/**
 * Сервис уведомлений.
 * <p>
 * Отправляет уведомления клиенту о статусе заказа: подтверждение, отправка, ошибка.
 * Является частью подсистемы оформления заказа, скрытой за фасадом
 * {@link pattern2_structural.struct2_facade.code.example3_order.OrderFacade}.
 */
public class NotificationService {

    /**
     * Отправляет подтверждение оформления заказа.
     *
     * @param email   адрес электронной почты клиента
     * @param orderId идентификатор заказа
     */
    public void sendOrderConfirmation(String email, String orderId) {
        System.out.println("[Уведомление] Письмо на " + email
                + ": заказ " + orderId + " подтверждён.");
    }

    /**
     * Отправляет уведомление об отправке заказа с номером отслеживания.
     *
     * @param email          адрес электронной почты клиента
     * @param trackingNumber номер отслеживания посылки
     */
    public void sendShippingNotification(String email, String trackingNumber) {
        System.out.println("[Уведомление] Письмо на " + email
                + ": посылка отправлена, трек: " + trackingNumber);
    }

    /**
     * Отправляет уведомление об ошибке оформления заказа.
     *
     * @param email  адрес электронной почты клиента
     * @param reason причина ошибки
     */
    public void sendOrderFailure(String email, String reason) {
        System.out.println("[Уведомление] Письмо на " + email
                + ": заказ не оформлен. Причина: " + reason);
    }
}
