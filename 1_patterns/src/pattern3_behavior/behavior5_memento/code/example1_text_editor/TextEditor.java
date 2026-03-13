package pattern3_behavior.behavior5_memento.code.example1_text_editor;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <b>Опекун (Caretaker)</b> — текстовый редактор.
 *
 * <p>Содержит {@link TextWindow текстовое окно} и управляет историей его состояний
 * через стек снимков. Опекун не имеет доступа к содержимому снимков и не может
 * их изменять — он лишь хранит и передаёт обратно Создателю при необходимости.</p>
 *
 * <p>Роль в паттерне: <b>Caretaker (Опекун)</b> — объект, который запрашивает
 * снимки у Создателя, хранит их и возвращает при отмене действия.</p>
 *
 * @see TextWindow Создатель (Originator)
 * @see TextWindowState Снимок (Memento)
 */
public class TextEditor {
    /** Текстовое окно (Создатель). */
    private TextWindow textWindow;

    /** История состояний окна, реализованная через стек ({@link Deque}). */
    private Deque<TextWindowState> savedTextWindow = new ArrayDeque<>();

    /**
     * Создаёт текстовый редактор с указанным текстовым окном.
     *
     * @param textWindow текстовое окно, состояние которого будет управляться
     */
    public TextEditor(TextWindow textWindow) {
        this.textWindow = textWindow;
    }

    /**
     * Записывает текст в текстовое окно.
     *
     * @param text текст для записи
     */
    public void write(String text) {
        textWindow.addText(text);
    }

    /**
     * Возвращает текущий текст из текстового окна.
     *
     * @return текст из текстового окна
     */
    public String print() {
        return textWindow.getCurrentText().toString();
    }

    /**
     * Сохранение состояния.
     *
     * <p>Создаёт снимок текущего состояния текстового окна
     * и помещает его на вершину стека истории.</p>
     */
    public void hitSave() {
        savedTextWindow.push(textWindow.save());
    }

    /**
     * Отмена действия (Undo).
     *
     * <p>Восстанавливает состояние текстового окна из снимка
     * на вершине стека. Если история пуста, операция игнорируется.</p>
     */
    public void hitUndo() {
        if (!savedTextWindow.isEmpty()) {
            textWindow.restore(savedTextWindow.pop());
        }
    }
}
