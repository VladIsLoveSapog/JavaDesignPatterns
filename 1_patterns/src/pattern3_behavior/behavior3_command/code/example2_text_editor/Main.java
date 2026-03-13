package pattern3_behavior.behavior3_command.code.example2_text_editor;

/**
 * Демонстрация паттерна Команда на примере текстового редактора с undo.
 *
 * <p>В отличие от примера 1 (операции с файлами), здесь команды поддерживают
 * метод {@link Command#undo()}, что позволяет отменять выполненные операции.
 * {@link Editor} (Invoker) хранит историю команд и предоставляет метод отмены.
 *
 * <p><i>Дополнительно: в данном примере также присутствует элемент
 * паттерна <b>Снимок (Memento)</b> — {@link History} хранит
 * последовательность команд для восстановления предыдущего состояния.</i>
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Текстовый редактор с undo ===\n");

        // Invoker — редактор, управляющий командами и историей
        Editor editor = new Editor();

        // Вставка текста "Hello " в начало
        Command insertHello = new InsertCommand(editor.getTextEditor(), 0, "Hello ");
        editor.executeCommand(insertHello);
        editor.printText(); // "Hello "

        // Вставка текста "World!" в конец
        Command insertWorld = new InsertCommand(
                editor.getTextEditor(),
                editor.getTextEditor().getText().length(),
                "World!");
        editor.executeCommand(insertWorld);
        editor.printText(); // "Hello World!"

        // Удаление "World!" (6 символов начиная с позиции 6)
        Command deleteWorld = new DeleteCommand(editor.getTextEditor(), 6, 6);
        editor.executeCommand(deleteWorld);
        editor.printText(); // "Hello "

        // Отмена удаления "World!" — текст восстановлен
        editor.undo();
        editor.printText(); // "Hello World!"

        // Отмена вставки "World!"
        editor.undo();
        editor.printText(); // "Hello "

        // Отмена вставки "Hello "
        editor.undo();
        editor.printText(); // ""

        // Попытка отмены при пустой истории
        editor.undo(); // "No commands to undo."
    }
}
