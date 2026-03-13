package pattern3_behavior.behavior1_chain.code.example6_purchase;

/**
 * Конкретный обработчик — Директор — в цепочке одобрения закупок.
 *
 * <p>Может одобрять закупки на <b>любую сумму</b> (лимит установлен
 * в {@link Double#MAX_VALUE}). Является последним звеном цепочки,
 * и запросы, дошедшие до него, всегда будут одобрены.
 *
 * <p><b>Роль в паттерне:</b> финальный (наиболее полномочный) узел
 * цепочки обязанностей. Выступает «ловушкой» для всех запросов,
 * которые не смогли одобрить {@link TeamLead} и {@link DepartmentHead}.
 *
 * <p><b>Пример использования:</b>
 * <pre>{@code
 * Approver director = new Director();
 * director.approve(new PurchaseRequest("Серверное оборудование", 15000));
 * // Директор одобрил закупку: Серверное оборудование (сумма: 15000.0) (лимит: 1.7976931348623157E308)
 * }</pre>
 *
 * @see BaseApprover
 * @see TeamLead
 * @see DepartmentHead
 */
public class Director extends BaseApprover {

    /**
     * Создаёт обработчик "Директор" с неограниченным лимитом одобрения
     * ({@link Double#MAX_VALUE}).
     */
    public Director() {
        super("Директор", Double.MAX_VALUE);
    }
}
