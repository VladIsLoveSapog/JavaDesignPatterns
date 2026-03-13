package pattern3_behavior.behavior5_memento.code.example3_drawing_editor;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <b>Опекун (Caretaker)</b> — история изменений графического редактора.
 *
 * <p>Управляет двумя стеками снимков: <b>undo</b> (отмена) и <b>redo</b> (повтор).
 * При сохранении нового состояния стек redo очищается, поскольку новое действие
 * делает «будущую» ветку истории неактуальной.</p>
 *
 * <p>Роль в паттерне: <b>Caretaker (Опекун)</b> — объект, который запрашивает
 * снимки у Создателя, хранит их и возвращает при необходимости.</p>
 *
 * @see Canvas Создатель (Originator)
 * @see CanvasMemento Снимок (Memento)
 */
public class DrawingHistory {
    /** Стек снимков для операции Undo (отмена). */
    private Deque<CanvasMemento> undoStack = new ArrayDeque<>();

    /** Стек снимков для операции Redo (повтор). */
    private Deque<CanvasMemento> redoStack = new ArrayDeque<>();

    /**
     * Сохраняет текущее состояние холста в историю.
     *
     * <p>Снимок помещается в стек undo, а стек redo очищается,
     * так как новое действие начинает новую ветку истории.</p>
     *
     * @param canvas холст, состояние которого необходимо сохранить
     */
    public void save(Canvas canvas) {
        undoStack.push(canvas.save());
        redoStack.clear();
    }

    /**
     * Отменяет последнее действие (Undo).
     *
     * <p>Текущее состояние холста сохраняется в стек redo,
     * затем холст восстанавливается из стека undo.</p>
     *
     * @param canvas холст, состояние которого необходимо откатить
     * @return {@code true}, если отмена выполнена; {@code false}, если история пуста
     */
    public boolean undo(Canvas canvas) {
        if (undoStack.isEmpty()) {
            System.out.println("Нечего отменять.");
            return false;
        }
        redoStack.push(canvas.save());
        canvas.restore(undoStack.pop());
        return true;
    }

    /**
     * Повторяет отменённое действие (Redo).
     *
     * <p>Текущее состояние холста сохраняется в стек undo,
     * затем холст восстанавливается из стека redo.</p>
     *
     * @param canvas холст, состояние которого необходимо повторить
     * @return {@code true}, если повтор выполнен; {@code false}, если redo-история пуста
     */
    public boolean redo(Canvas canvas) {
        if (redoStack.isEmpty()) {
            System.out.println("Нечего повторять.");
            return false;
        }
        undoStack.push(canvas.save());
        canvas.restore(redoStack.pop());
        return true;
    }
}
