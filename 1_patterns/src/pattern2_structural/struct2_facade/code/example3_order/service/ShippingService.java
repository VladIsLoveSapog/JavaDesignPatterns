package pattern2_structural.struct2_facade.code.example3_order.service;

/**
 * Сервис доставки.
 * <p>
 * Организует доставку заказа по указанному адресу и возвращает номер отслеживания.
 * Является частью подсистемы оформления заказа, скрытой за фасадом
 * {@link pattern2_structural.struct2_facade.code.example3_order.OrderFacade}.
 */
public class ShippingService {

    /**
     * Организует доставку заказа.
     *
     * @param orderId идентификатор заказа
     * @param address адрес доставки
     * @return номер отслеживания посылки
     */
    public String arrangeShipping(String orderId, String address) {
        String trackingNumber = "TRK-" + orderId + "-001";
        System.out.println("[Доставка] Заказ " + orderId + " отправлен по адресу: "
                + address + " (трек: " + trackingNumber + ")");
        return trackingNumber;
    }
}
