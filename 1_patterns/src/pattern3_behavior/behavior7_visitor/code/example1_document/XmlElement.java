package pattern3_behavior.behavior7_visitor.code.example1_document;

import java.util.HashMap;
import java.util.Map;

/**
 * Конкретный элемент документа — XML-узел.
 *
 * <p>Хранит данные в виде пар ключ-значение ({@link Map}) и умеет
 * выводить их в XML-подобном формате с тегами. Реализует {@link Element},
 * чтобы посетитель мог выполнить над ним операцию.
 *
 * <p><b>Роль в паттерне:</b> конкретный элемент (ConcreteElement).
 * В методе {@link #accept(DocVisitor)} вызывает
 * {@code visitor.doForXmlElement(this)}, передавая себя посетителю.
 */
public class XmlElement implements Element {

    /** Хранилище данных XML-узла: имя тега → содержимое. */
    private final Map<String, String> xmlTree = new HashMap<>();

    public XmlElement() {
    }

    /**
     * Добавляет пару тег-значение в XML-узел.
     *
     * @param key   имя XML-тега
     * @param data  содержимое тега
     */
    void addData(String key, String data) {
        xmlTree.put(key, data);
    }

    /**
     * Выводит содержимое XML-узла в консоль в XML-подобном формате.
     */
    public void printXmlTree() {
        xmlTree.forEach((k, v) -> {
            System.out.println("<" + k + ">");
            System.out.println("\t" + v);
            System.out.println("</" + k + ">");
        });
    }

    /**
     * Принимает посетителя, вызывая у него метод для обработки XML-элемента.
     *
     * <p>Именно здесь происходит двойная диспетчеризация: элемент
     * «знает» свой тип и вызывает конкретный метод посетителя.
     *
     * @param visitor посетитель, выполняющий операцию
     */
    @Override
    public void accept(DocVisitor visitor) {
        // Передаём себя (this) в метод посетителя для XML-элементов
        visitor.doForXmlElement(this);
    }
}
