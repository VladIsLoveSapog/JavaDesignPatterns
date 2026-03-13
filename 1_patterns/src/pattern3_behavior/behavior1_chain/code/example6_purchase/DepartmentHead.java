package pattern3_behavior.behavior1_chain.code.example6_purchase;

/**
 * Конкретный обработчик — Начальник отдела — в цепочке одобрения закупок.
 *
 * <p>Может одобрять закупки на сумму до <b>5 000</b> условных единиц.
 * Если сумма запроса превышает этот лимит, запрос передаётся следующему
 * обработчику в цепочке (как правило, {@link Director}).
 *
 * <p><b>Роль в паттерне:</b> средний узел цепочки обязанностей,
 * расположенный между {@link TeamLead} и {@link Director}. Обрабатывает
 * закупки среднего масштаба, которые выходят за рамки полномочий тимлида.
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * Approver head = new DepartmentHead();
 * head.setNext(new Director());
 * head.approve(new PurchaseRequest("Ноутбуки для отдела", 3500));
 * // Начальник отдела одобрил закупку: Ноутбуки для отдела (сумма: 3500.0) (лимит: 5000.0)
 * }</pre>
 *
 * @see BaseApprover
 * @see TeamLead
 * @see Director
 */
public class DepartmentHead extends BaseApprover {

    /**
     * Создаёт обработчик "Начальник отдела" с лимитом одобрения <b>5 000</b>.
     */
    public DepartmentHead() {
        super("Начальник отдела", 5000);
    }
}
