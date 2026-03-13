package pattern3_behavior.behavior1_chain.code.example6_purchase;

/**
 * Конкретный обработчик — Тимлид — в цепочке одобрения закупок.
 *
 * <p>Может одобрять закупки на сумму до <b>1 000</b> условных единиц.
 * Если сумма запроса превышает этот лимит, запрос передаётся следующему
 * обработчику в цепочке (как правило, {@link DepartmentHead}).
 *
 * <p><b>Роль в паттерне:</b> первый (наименее полномочный) узел цепочки
 * обязанностей. Обрабатывает мелкие закупки самостоятельно, не нагружая
 * вышестоящее руководство.
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * Approver teamLead = new TeamLead();
 * teamLead.setNext(new DepartmentHead());
 * teamLead.approve(new PurchaseRequest("Канцелярия", 500));
 * // Тимлид одобрил закупку: Канцелярия (сумма: 500.0) (лимит: 1000.0)
 * }</pre>
 *
 * @see BaseApprover
 * @see DepartmentHead
 * @see Director
 */
public class TeamLead extends BaseApprover {

    /**
     * Создаёт обработчик "Тимлид" с лимитом одобрения <b>1 000</b>.
     */
    public TeamLead() {
        super("Тимлид", 1000);
    }
}
