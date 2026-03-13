package pattern2_structural.struct2_facade.code.example3_order;

import pattern2_structural.struct2_facade.code.example3_order.service.*;

/**
 * Фасад для оформления онлайн-заказа.
 * <p>
 * Координирует работу четырёх сервисов подсистемы: {@link InventoryService},
 * {@link PaymentService}, {@link ShippingService} и {@link NotificationService}.
 * <p>
 * Метод {@link #placeOrder} выполняет полный цикл оформления заказа
 * (проверка наличия, резервирование, оплата, доставка, уведомление)
 * и реализует компенсирующие действия при ошибках на любом этапе.
 */
public class OrderFacade {

    /** Сервис управления складом. */
    private final InventoryService inventoryService;

    /** Сервис обработки платежей. */
    private final PaymentService paymentService;

    /** Сервис доставки. */
    private final ShippingService shippingService;

    /** Сервис уведомлений. */
    private final NotificationService notificationService;

    public OrderFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
    }

    /**
     * Оформляет заказ: полный цикл от проверки наличия до уведомления клиента.
     * <p>
     * Последовательность:
     * <ol>
     *   <li>Проверка наличия товара на складе</li>
     *   <li>Резервирование товара</li>
     *   <li>Обработка платежа (при ошибке — снятие резерва)</li>
     *   <li>Организация доставки (при ошибке — возврат средств и снятие резерва)</li>
     *   <li>Отправка уведомлений клиенту</li>
     * </ol>
     *
     * @param productId идентификатор товара
     * @param amount    сумма заказа
     * @param address   адрес доставки
     * @param email     email клиента для уведомлений
     */
    public void placeOrder(String productId, double amount, String address, String email) {
        String orderId = "ORD-" + System.currentTimeMillis();
        System.out.println("=== Оформление заказа " + orderId + " ===");

        // 1. Проверка наличия
        if (!inventoryService.isAvailable(productId)) {
            notificationService.sendOrderFailure(email, "товар " + productId + " отсутствует на складе");
            return;
        }

        // 2. Резервирование
        inventoryService.reserve(productId);

        // 3. Оплата
        if (!paymentService.processPayment(orderId, amount)) {
            inventoryService.release(productId);
            notificationService.sendOrderFailure(email, "ошибка оплаты");
            return;
        }

        // 4. Доставка
        String trackingNumber;
        try {
            trackingNumber = shippingService.arrangeShipping(orderId, address);
        } catch (Exception e) {
            paymentService.refund(orderId, amount);
            inventoryService.release(productId);
            notificationService.sendOrderFailure(email, "ошибка организации доставки");
            return;
        }

        // 5. Уведомления
        notificationService.sendOrderConfirmation(email, orderId);
        notificationService.sendShippingNotification(email, trackingNumber);

        System.out.println("=== Заказ " + orderId + " успешно оформлен ===");
    }
}
