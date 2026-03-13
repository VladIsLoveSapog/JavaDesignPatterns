package pattern3_behavior.behavior5_memento.code.example1_text_editor;

/**
 * Демонстрация паттерна <b>Memento (Снимок)</b> на примере текстового редактора.
 *
 * <p>Сценарий:</p>
 * <ol>
 *     <li>Вводим две строки текста</li>
 *     <li>Сохраняем состояние (снимок)</li>
 *     <li>Вводим ещё одну строку</li>
 *     <li>Отменяем последнее действие (undo) — текст возвращается к состоянию после шага 2</li>
 * </ol>
 *
 * <p>Ожидаемый вывод:</p>
 * <pre>
 * === Текстовый редактор (Memento) ===
 * Ввод: "The Memento Design Pattern"
 * Ввод: "How to implement it in Java?"
 * Сохранение состояния...
 * Ввод: "Buy milk and eggs before coming home"
 * Текст до отмены:
 * The Memento Design Pattern
 * How to implement it in Java?
 * Buy milk and eggs before coming home
 *
 * Отмена последнего действия (Undo)...
 * Текст после отмены:
 * The Memento Design Pattern
 * How to implement it in Java?
 * </pre>
 */
public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(new TextWindow());

        System.out.println("=== Текстовый редактор (Memento) ===");

        // Ввод двух строк
        textEditor.write("The Memento Design Pattern\n");
        System.out.println("Ввод: \"The Memento Design Pattern\"");
        textEditor.write("How to implement it in Java?\n");
        System.out.println("Ввод: \"How to implement it in Java?\"");

        // Сохранение состояния
        textEditor.hitSave();
        System.out.println("Сохранение состояния...");

        // Ввод ещё одной строки
        textEditor.write("Buy milk and eggs before coming home\n");
        System.out.println("Ввод: \"Buy milk and eggs before coming home\"");

        // Вывод текста до отмены
        System.out.println("Текст до отмены:");
        System.out.println(textEditor.print());

        // Отмена последнего действия
        textEditor.hitUndo();
        System.out.println("Отмена последнего действия (Undo)...");
        System.out.println("Текст после отмены:");
        System.out.println(textEditor.print());
    }
}
