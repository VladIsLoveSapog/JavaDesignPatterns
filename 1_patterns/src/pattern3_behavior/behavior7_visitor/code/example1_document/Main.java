package pattern3_behavior.behavior7_visitor.code.example1_document;

/**
 * Демонстрация паттерна Посетитель на примере обхода документа.
 *
 * <p>Документ содержит элементы в разных нотациях (JSON и XML).
 * Посетитель {@link SerializeDocVisitor} обходит все элементы
 * и сериализует каждый в его собственном формате.
 *
 * <p><b>Ключевой момент:</b> клиентский код не знает конкретных типов
 * элементов — он просто вызывает {@code doc.accept(visitor)}, а механизм
 * двойной диспетчеризации автоматически направляет вызов к нужному методу
 * посетителя.
 */
public class Main {
    public static void main(String[] args) {
        // Создаём посетителя для сериализации элементов
        DocVisitor visitor = new SerializeDocVisitor();

        // Создаём документ — контейнер для элементов разных типов
        Document doc = new Document();

        // Добавляем JSON-элемент с данными
        var jsonNode = new JsonElement();
        jsonNode.addData("text", "Ура, последний паттерн!");
        jsonNode.addData("weekNumber", "8");
        doc.add(jsonNode);

        // Добавляем XML-элемент с данными
        var xmlNode = new XmlElement();
        xmlNode.addData("text", "Но это только начало. Надо еще сдать все лабы.");
        doc.add(xmlNode);

        // Запускаем обход: каждый элемент принимает посетителя,
        // который выводит содержимое в соответствующем формате
        doc.accept(visitor);
    }
}
