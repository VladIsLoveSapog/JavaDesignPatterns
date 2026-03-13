package pattern3_behavior.behavior1_chain.code.example6_purchase;

/**
 * Абстрактный базовый обработчик в цепочке одобрения закупок.
 *
 * <p>Реализует общую логику паттерна "Цепочка обязанностей" (Chain of Responsibility):
 * если сумма запроса {@link PurchaseRequest#getAmount()} не превышает лимит
 * текущего обработчика ({@link #approvalLimit}), запрос одобряется и цепочка
 * останавливается. Иначе запрос передаётся следующему звену ({@link #nextApprover}).
 *
 * <p><b>Роль в паттерне:</b> содержит шаблонную реализацию метода
 * {@link #approve(PurchaseRequest)}, избавляя конкретных обработчиков
 * ({@link TeamLead}, {@link DepartmentHead}, {@link Director}) от дублирования
 * логики маршрутизации запросов.
 *
 * <p><b>Принцип работы:</b>
 * <ul>
 *     <li>Если {@code request.getAmount() <= approvalLimit} — обработчик одобряет запрос и цепочка прерывается.</li>
 *     <li>Если лимит превышен и есть следующий обработчик — запрос передаётся дальше.</li>
 *     <li>Если лимит превышен и следующего обработчика нет — выводится сообщение об отказе.</li>
 * </ul>
 *
 * @see Approver
 * @see PurchaseRequest
 */
public abstract class BaseApprover implements Approver {

    /**
     * Ссылка на следующий обработчик в цепочке.
     *
     * <p>Если {@code null}, текущий обработчик является последним звеном,
     * и при невозможности одобрения запрос будет отклонён.
     */
    protected Approver nextApprover;

    /**
     * Название роли обработчика (например, "Тимлид", "Начальник отдела").
     *
     * <p>Используется в сообщениях при одобрении или передаче запроса.
     */
    protected String roleName;

    /**
     * Максимальная сумма закупки, которую данный обработчик может одобрить.
     *
     * <p>Если сумма запроса {@link PurchaseRequest#getAmount()} не превышает
     * это значение, обработчик одобряет запрос самостоятельно.
     */
    protected double approvalLimit;

    /**
     * Создаёт обработчик с указанным названием роли и лимитом одобрения.
     *
     * @param roleName      название роли (например, "Тимлид")
     * @param approvalLimit максимальная сумма, которую может одобрить данный обработчик
     */
    public BaseApprover(String roleName, double approvalLimit) {
        this.roleName = roleName;
        this.approvalLimit = approvalLimit;
    }

    /**
     * Устанавливает следующий обработчик в цепочке одобрения.
     *
     * <p>Позволяет строить цепочку вызовом:
     * <pre>{@code
     * teamLead.setNext(departmentHead);
     * departmentHead.setNext(director);
     * }</pre>
     *
     * @param next следующий обработчик в цепочке
     */
    @Override
    public void setNext(Approver next) {
        this.nextApprover = next;
    }

    /**
     * Рассматривает запрос на закупку и принимает решение.
     *
     * <p><b>Алгоритм:</b>
     * <ol>
     *     <li>Если сумма запроса {@code <=} {@link #approvalLimit} — одобряет запрос
     *         и выводит сообщение об успешном одобрении. Цепочка останавливается.</li>
     *     <li>Если сумма превышает лимит и установлен {@link #nextApprover} —
     *         выводит сообщение о передаче и делегирует запрос следующему обработчику.</li>
     *     <li>Если сумма превышает лимит и следующего обработчика нет —
     *         выводит сообщение о невозможности одобрения.</li>
     * </ol>
     *
     * @param request запрос на закупку для рассмотрения
     */
    @Override
    public void approve(PurchaseRequest request) {
        if (request.getAmount() <= approvalLimit) {
            System.out.println(roleName + " одобрил закупку: " + request + " (лимит: " + approvalLimit + ")");
        } else if (nextApprover != null) {
            System.out.println(roleName + " не может одобрить " + request + " (лимит: " + approvalLimit + "). Передаём дальше.");
            nextApprover.approve(request);
        } else {
            System.out.println(roleName + ": Запрос " + request + " не может быть одобрен — превышены все лимиты.");
        }
    }
}
