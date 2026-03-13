package pattern3_behavior.behavior1_chain.code.example6_purchase;

/**
 * Демонстрация паттерна "Цепочка обязанностей" (Chain of Responsibility)
 * на примере цепочки одобрения закупок.
 *
 * <p><b>Сценарий:</b> в организации существует иерархия лиц, уполномоченных
 * одобрять закупки. Каждый уровень имеет свой лимит:
 * <ul>
 *     <li>{@link TeamLead} — до 1 000</li>
 *     <li>{@link DepartmentHead} — до 5 000</li>
 *     <li>{@link Director} — без ограничений</li>
 * </ul>
 *
 * <p><b>Классическая цепочка обязанностей:</b> запрос {@link PurchaseRequest}
 * передаётся по цепочке от тимлида к директору. Как только один из обработчиков
 * может одобрить запрос (сумма не превышает его лимит), он обрабатывает запрос
 * и цепочка <b>останавливается</b>. Остальные обработчики не задействуются.
 *
 * <p><b>Цепочка:</b> {@link TeamLead} &rarr; {@link DepartmentHead} &rarr; {@link Director}
 *
 * @see Approver
 * @see BaseApprover
 * @see PurchaseRequest
 */
public class Main {

    public static void main(String[] args) {
        // Создаём обработчиков
        Approver teamLead = new TeamLead();
        Approver departmentHead = new DepartmentHead();
        Approver director = new Director();

        // Строим цепочку: Тимлид → Начальник отдела → Директор
        teamLead.setNext(departmentHead);
        departmentHead.setNext(director);

        // Тест 1: Небольшая закупка — одобряет Тимлид
        PurchaseRequest request1 = new PurchaseRequest("Канцелярия", 500);
        System.out.println("=== Запрос: " + request1 + " ===");
        teamLead.approve(request1);

        System.out.println("---------------------------------------------------------------------");

        // Тест 2: Средняя закупка — Тимлид передаёт, одобряет Начальник отдела
        PurchaseRequest request2 = new PurchaseRequest("Ноутбуки для отдела", 3500);
        System.out.println("=== Запрос: " + request2 + " ===");
        teamLead.approve(request2);

        System.out.println("---------------------------------------------------------------------");

        // Тест 3: Крупная закупка — проходит всю цепочку, одобряет Директор
        PurchaseRequest request3 = new PurchaseRequest("Серверное оборудование", 15000);
        System.out.println("=== Запрос: " + request3 + " ===");
        teamLead.approve(request3);
    }
}
