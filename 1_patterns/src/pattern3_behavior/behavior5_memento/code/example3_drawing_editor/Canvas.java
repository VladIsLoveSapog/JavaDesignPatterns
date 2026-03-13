package pattern3_behavior.behavior5_memento.code.example3_drawing_editor;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Создатель (Originator)</b> — холст графического редактора.
 *
 * <p>Содержит список фигур ({@link Shape}), позволяет добавлять и удалять фигуры,
 * а также создавать снимки текущего состояния и восстанавливаться из них.</p>
 *
 * <p>Роль в паттерне: <b>Originator (Создатель)</b> — объект, который умеет
 * создавать снимки своего состояния ({@link #save()}) и восстанавливаться
 * из ранее сохранённого снимка ({@link #restore(CanvasMemento)}).</p>
 *
 * @see CanvasMemento Снимок (Memento)
 * @see DrawingHistory Опекун (Caretaker)
 */
public class Canvas {
    /** Текущий список фигур на холсте. */
    private List<Shape> shapes = new ArrayList<>();

    /**
     * Добавляет фигуру на холст.
     *
     * @param shape фигура для добавления
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println("Добавлена фигура: " + shape);
    }

    /**
     * Возвращает текущий список фигур на холсте.
     *
     * @return список фигур
     */
    public List<Shape> getShapes() {
        return shapes;
    }

    /**
     * Создаёт снимок текущего состояния холста.
     *
     * <p>Внутри {@link CanvasMemento} создаётся защитная копия списка фигур,
     * поэтому дальнейшие изменения на холсте не затронут снимок.</p>
     *
     * @return снимок текущего состояния
     */
    public CanvasMemento save() {
        return new CanvasMemento(shapes);
    }

    /**
     * Восстанавливает состояние холста из снимка.
     *
     * <p>Текущий список фигур заменяется копией из снимка.</p>
     *
     * @param memento снимок, к которому необходимо вернуться
     */
    public void restore(CanvasMemento memento) {
        shapes = new ArrayList<>(memento.getShapes());
    }

    /**
     * Выводит текущее содержимое холста в консоль.
     */
    public void printCanvas() {
        System.out.println("Холст: " + (shapes.isEmpty() ? "[пусто]" : shapes));
    }
}
