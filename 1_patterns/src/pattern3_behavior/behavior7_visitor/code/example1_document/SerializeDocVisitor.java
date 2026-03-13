package pattern3_behavior.behavior7_visitor.code.example1_document;

/**
 * Конкретный посетитель — сериализация (вывод) элементов документа.
 *
 * <p>Реализует {@link DocVisitor} и для каждого типа элемента вызывает
 * его собственный метод печати. Это позволяет добавить операцию
 * сериализации без изменения классов элементов.
 *
 * <p><b>Роль в паттерне:</b> конкретный посетитель (ConcreteVisitor).
 * Содержит реализацию операции для каждого типа элемента.
 * Если потребуется новая операция (например, валидация), достаточно
 * создать нового посетителя, не трогая классы элементов.
 */
public class SerializeDocVisitor implements DocVisitor {

    /**
     * Сериализует XML-элемент — выводит его содержимое в XML-формате.
     *
     * @param element XML-узел для сериализации
     */
    @Override
    public void doForXmlElement(XmlElement element) {
        element.printXmlTree();
    }

    /**
     * Сериализует JSON-элемент — выводит его содержимое в JSON-формате.
     *
     * @param element JSON-узел для сериализации
     */
    @Override
    public void doForJSONElement(JsonElement element) {
        element.printJsonTree();
    }
}
