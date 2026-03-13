package pattern3_behavior.behavior5_memento.code.example3_drawing_editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <b>Снимок (Memento)</b> состояния холста.
 *
 * <p>Хранит неизменяемую копию списка фигур, которая была актуальна
 * на момент создания снимка. При создании выполняется <i>защитная копия</i>
 * ({@code new ArrayList<>(shapes)}), чтобы последующие изменения на холсте
 * не затронули снимок.</p>
 *
 * <p>Роль в паттерне: <b>Memento (Снимок)</b> — объект, инкапсулирующий
 * внутреннее состояние {@link Canvas Создателя}.</p>
 *
 * @see Canvas Создатель (Originator)
 * @see DrawingHistory Опекун (Caretaker)
 */
public class CanvasMemento {
    /** Защитная копия списка фигур на момент сохранения. */
    private final List<Shape> shapes;

    /**
     * Создаёт снимок с защитной копией переданного списка фигур.
     *
     * @param shapes список фигур на момент сохранения
     */
    public CanvasMemento(List<Shape> shapes) {
        this.shapes = new ArrayList<>(shapes);
    }

    /**
     * Возвращает неизменяемое представление сохранённых фигур.
     *
     * @return неизменяемый список фигур на момент создания снимка
     */
    public List<Shape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }
}
