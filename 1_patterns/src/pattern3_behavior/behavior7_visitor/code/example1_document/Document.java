package pattern3_behavior.behavior7_visitor.code.example1_document;

import java.util.ArrayList;
import java.util.List;

/**
 * Составной документ, содержащий коллекцию элементов разных типов.
 *
 * <p>Выступает одновременно как контейнер элементов и как элемент сам
 * по себе (реализует {@link Element}). При вызове {@link #accept(DocVisitor)}
 * документ делегирует посетителя каждому из своих дочерних элементов,
 * обеспечивая обход всей структуры.
 *
 * <p><b>Аналогия с Composite:</b> Document похож на узел-контейнер
 * в паттерне Компоновщик — он не выполняет операцию сам, а передаёт
 * посетителя дочерним элементам.
 */
public class Document implements Element {

    /** Список элементов документа (XML-узлы, JSON-узлы и др.). */
    List<Element> elements = new ArrayList<>();

    public Document() {
    }

    /**
     * Добавляет элемент в документ.
     *
     * @param element элемент для добавления (XML, JSON и т.д.)
     */
    void add(Element element) {
        elements.add(element);
    }

    /**
     * Принимает посетителя и применяет его к каждому элементу документа.
     *
     * <p>Обход выполняется последовательно: посетитель получает
     * каждый элемент по очереди, а конкретный тип элемента определяет,
     * какой метод посетителя будет вызван (двойная диспетчеризация).
     *
     * @param visitor посетитель, выполняющий операцию над элементами
     */
    @Override
    public void accept(DocVisitor visitor) {
        // Делегируем посетителя каждому дочернему элементу
        elements.forEach(el -> el.accept(visitor));
    }
}
