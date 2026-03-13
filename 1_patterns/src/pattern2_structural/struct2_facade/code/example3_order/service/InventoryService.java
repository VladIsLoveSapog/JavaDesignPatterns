package pattern2_structural.struct2_facade.code.example3_order.service;

/**
 * Сервис управления складом.
 * <p>
 * Отвечает за проверку наличия товаров, резервирование и освобождение запасов.
 * Является частью подсистемы оформления заказа, скрытой за фасадом
 * {@link pattern2_structural.struct2_facade.code.example3_order.OrderFacade}.
 */
public class InventoryService {

    /**
     * Проверяет, доступен ли товар на складе.
     *
     * @param productId идентификатор товара
     * @return {@code true}, если товар есть в наличии
     */
    public boolean isAvailable(String productId) {
        System.out.println("[Склад] Проверка наличия товара: " + productId);
        return true;
    }

    /**
     * Резервирует товар на складе.
     *
     * @param productId идентификатор товара
     */
    public void reserve(String productId) {
        System.out.println("[Склад] Товар зарезервирован: " + productId);
    }

    /**
     * Снимает резерв с товара (компенсация при ошибке).
     *
     * @param productId идентификатор товара
     */
    public void release(String productId) {
        System.out.println("[Склад] Резерв снят: " + productId);
    }
}
