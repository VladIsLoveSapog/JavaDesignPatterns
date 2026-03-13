package pattern3_behavior.behavior5_memento.code.example3_drawing_editor;

/**
 * Демонстрация паттерна <b>Memento (Снимок)</b> на примере графического редактора
 * с поддержкой <b>Undo</b> и <b>Redo</b>.
 *
 * <p>Отличие от примеров 1 и 2: состояние — это коллекция объектов ({@code List<Shape>}),
 * а не примитивные поля. Используются два стека (undo + redo) для полноценной
 * навигации по истории.</p>
 *
 * <p>Сценарий:</p>
 * <ol>
 *     <li>Добавляем три фигуры с сохранением после каждой</li>
 *     <li>Отменяем последнее действие (undo) — убираем третью фигуру</li>
 *     <li>Повторяем отменённое действие (redo) — возвращаем третью фигуру</li>
 *     <li>Добавляем новую фигуру — стек redo очищается</li>
 *     <li>Попытка redo после нового действия — redo недоступен</li>
 * </ol>
 *
 * <p>Ожидаемый вывод:</p>
 * <pre>
 * === Графический редактор (Memento + Undo/Redo) ===
 * Добавлена фигура: Circle(100, 100, Red)
 * Добавлена фигура: Rectangle(200, 150, Blue)
 * Добавлена фигура: Triangle(300, 200, Green)
 * Холст: [Circle(100, 100, Red), Rectangle(200, 150, Blue), Triangle(300, 200, Green)]
 *
 * --- Undo ---
 * Холст: [Circle(100, 100, Red), Rectangle(200, 150, Blue)]
 *
 * --- Redo ---
 * Холст: [Circle(100, 100, Red), Rectangle(200, 150, Blue), Triangle(300, 200, Green)]
 *
 * --- Новое действие (очищает redo) ---
 * Добавлена фигура: Star(50, 50, Yellow)
 * Холст: [Circle(100, 100, Red), Rectangle(200, 150, Blue), Triangle(300, 200, Green), Star(50, 50, Yellow)]
 *
 * --- Попытка Redo после нового действия ---
 * Нечего повторять.
 * </pre>
 */
public class Main {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        DrawingHistory history = new DrawingHistory();

        System.out.println("=== Графический редактор (Memento + Undo/Redo) ===");

        // Добавляем фигуры с сохранением после каждой
        history.save(canvas);
        canvas.addShape(new Shape("Circle", 100, 100, "Red"));

        history.save(canvas);
        canvas.addShape(new Shape("Rectangle", 200, 150, "Blue"));

        history.save(canvas);
        canvas.addShape(new Shape("Triangle", 300, 200, "Green"));

        canvas.printCanvas();

        // Undo — убираем Triangle
        System.out.println("\n--- Undo ---");
        history.undo(canvas);
        canvas.printCanvas();

        // Redo — возвращаем Triangle
        System.out.println("\n--- Redo ---");
        history.redo(canvas);
        canvas.printCanvas();

        // Новое действие — очищает redo
        System.out.println("\n--- Новое действие (очищает redo) ---");
        history.save(canvas);
        canvas.addShape(new Shape("Star", 50, 50, "Yellow"));
        canvas.printCanvas();

        // Попытка Redo — стек пуст
        System.out.println("\n--- Попытка Redo после нового действия ---");
        history.redo(canvas);
    }
}
