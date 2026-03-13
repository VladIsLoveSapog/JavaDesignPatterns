package pattern2_structural.struct2_facade.code.example3_order;

import pattern2_structural.struct2_facade.code.example3_order.service.*;

/**
 * Демонстрация паттерна Фасад на примере оформления онлайн-заказа.
 * <p>
 * <b>Часть 1 (без фасада):</b> клиент вручную координирует четыре сервиса подсистемы,
 * самостоятельно реализуя логику проверок и компенсаций при ошибках.
 * <p>
 * <b>Часть 2 (с фасадом):</b> один вызов {@link OrderFacade#placeOrder} —
 * вся сложность скрыта внутри фасада.
 */
public class OrderMain {
    public static void main(String[] args) {
        String productId = "LAPTOP-001";
        double amount = 89_990.0;
        String address = "г. Москва, ул. Пушкина, д. 10";
        String email = "client@example.com";

        // =====================================================================
        // Часть 1: без фасада — ручная координация четырёх сервисов
        // =====================================================================
        System.out.println("*** Без фасада ***");

        InventoryService inventory = new InventoryService();
        PaymentService payment = new PaymentService();
        ShippingService shipping = new ShippingService();
        NotificationService notification = new NotificationService();

        String orderId = "ORD-MANUAL-001";

        // Клиент должен сам знать правильный порядок и обрабатывать ошибки
        if (inventory.isAvailable(productId)) {
            inventory.reserve(productId);

            if (payment.processPayment(orderId, amount)) {
                String tracking = shipping.arrangeShipping(orderId, address);
                notification.sendOrderConfirmation(email, orderId);
                notification.sendShippingNotification(email, tracking);
            } else {
                // Клиент сам реализует компенсацию
                inventory.release(productId);
                notification.sendOrderFailure(email, "ошибка оплаты");
            }
        } else {
            notification.sendOrderFailure(email, "товар отсутствует");
        }

        System.out.println();

        // =====================================================================
        // Часть 2: с фасадом — один вызов, вся сложность внутри
        // =====================================================================
        System.out.println("*** С фасадом ***");

        OrderFacade orderFacade = new OrderFacade();
        orderFacade.placeOrder(productId, amount, address, email);

        // Клиенту не нужно знать о сервисах, порядке вызовов и компенсациях.
        // При изменении подсистемы (новый сервис, другой порядок) —
        // клиентский код не меняется.
    }
}
