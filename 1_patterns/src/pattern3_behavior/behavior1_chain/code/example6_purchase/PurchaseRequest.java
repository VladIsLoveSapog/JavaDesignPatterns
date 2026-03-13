package pattern3_behavior.behavior1_chain.code.example6_purchase;

/**
 * Класс запроса на закупку в паттерне "Цепочка обязанностей" (Chain of Responsibility).
 *
 * <p>Представляет собой объект-запрос, который передаётся по цепочке обработчиков
 * ({@link Approver}). Каждый обработчик анализирует сумму запроса ({@link #amount})
 * и решает, может ли он одобрить закупку самостоятельно.
 *
 * <p><b>Роль в паттерне:</b> инкапсулирует данные запроса (описание и сумму),
 * которые используются обработчиками для принятия решения об одобрении.
 * Объект передаётся по цепочке от {@link TeamLead} через {@link DepartmentHead}
 * к {@link Director}, пока один из них не одобрит запрос.
 *
 * @see Approver
 * @see BaseApprover
 */
public class PurchaseRequest {

    /**
     * Описание закупки (например, "Канцелярия", "Ноутбуки для отдела").
     */
    private final String description;

    /**
     * Сумма закупки в условных единицах.
     *
     * <p>Именно это значение сравнивается с лимитом каждого обработчика
     * в методе {@link BaseApprover#approve(PurchaseRequest)}.
     */
    private final double amount;

    /**
     * Создаёт новый запрос на закупку.
     *
     * @param description описание закупки
     * @param amount      сумма закупки
     */
    public PurchaseRequest(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    /**
     * Возвращает описание закупки.
     *
     * @return описание закупки
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает сумму закупки.
     *
     * <p>Используется обработчиками ({@link BaseApprover}) для сравнения
     * с их лимитом одобрения ({@code approvalLimit}).
     *
     * @return сумма закупки
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Возвращает строковое представление запроса в формате:
     * {@code "описание (сумма: X)"}.
     *
     * @return строковое представление запроса
     */
    @Override
    public String toString() {
        return description + " (сумма: " + amount + ")";
    }
}
