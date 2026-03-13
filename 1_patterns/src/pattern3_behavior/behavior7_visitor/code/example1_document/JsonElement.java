package pattern3_behavior.behavior7_visitor.code.example1_document;

import java.util.HashMap;
import java.util.Map;

/**
 * Конкретный элемент документа — JSON-узел.
 *
 * <p>Хранит данные в виде пар ключ-значение ({@link Map}) и умеет
 * выводить их в JSON-подобном формате. Реализует {@link Element},
 * чтобы посетитель мог выполнить над ним операцию.
 *
 * <p><b>Роль в паттерне:</b> конкретный элемент (ConcreteElement).
 * В методе {@link #accept(DocVisitor)} вызывает
 * {@code visitor.doForJSONElement(this)}, передавая себя посетителю —
 * это вторая часть двойной диспетчеризации.
 */
public class JsonElement implements Element {

    /** Хранилище данных JSON-узла: ключ → значение. */
    private final Map<String, String> jsonTree = new HashMap<>();

    public JsonElement() {
    }

    /**
     * Добавляет пару ключ-значение в JSON-узел.
     *
     * @param key   ключ элемента
     * @param data  значение элемента
     */
    void addData(String key, String data) {
        jsonTree.put(key, data);
    }

    /**
     * Выводит содержимое JSON-узла в консоль в JSON-подобном формате.
     */
    public void printJsonTree() {
        jsonTree.forEach((k, v) -> {
            System.out.println("{");
            System.out.println("\t" + k + ": " + v);
            System.out.println("}");
        });
    }

    /**
     * Принимает посетителя, вызывая у него метод для обработки JSON-элемента.
     *
     * <p>Именно здесь происходит двойная диспетчеризация: элемент
     * «знает» свой тип и вызывает конкретный метод посетителя.
     *
     * @param visitor посетитель, выполняющий операцию
     */
    @Override
    public void accept(DocVisitor visitor) {
        // Передаём себя (this) в метод посетителя для JSON-элементов
        visitor.doForJSONElement(this);
    }
}
