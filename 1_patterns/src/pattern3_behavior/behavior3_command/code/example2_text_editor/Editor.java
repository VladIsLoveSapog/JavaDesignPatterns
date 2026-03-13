package pattern3_behavior.behavior3_command.code.example2_text_editor;

/**
 * Редактор — инициатор выполнения команд (роль <b>Invoker</b>).
 *
 * <p>Принимает объекты-команды ({@link Command}), выполняет их и сохраняет
 * в {@link History} для возможности отмены. Invoker не знает деталей
 * конкретных команд — он работает через общий интерфейс {@link Command}.
 *
 * <p><b>Делегирование:</b> {@code Editor} владеет получателем
 * ({@link TextEditor}) и историей ({@link History}), координируя
 * их взаимодействие. Команды создаются клиентским кодом и передаются
 * в {@link #executeCommand(Command)}.
 *
 * <p><b>Когда использовать:</b> когда нужен центральный объект,
 * управляющий выполнением и отменой операций над получателем.
 */
public class Editor {

    /** Получатель — текстовый редактор, над которым выполняются команды. */
    private TextEditor textEditor = new TextEditor();

    /** История выполненных команд для поддержки undo. */
    private History history = new History();

    /**
     * Возвращает получателя — текстовый редактор.
     *
     * @return объект {@link TextEditor}
     */
    public TextEditor getTextEditor() {
        return textEditor;
    }

    /**
     * Выполняет команду и добавляет её в историю.
     *
     * @param cmd команда для выполнения
     */
    public void executeCommand(Command cmd) {
        cmd.execute();
        history.push(cmd);
    }

    /**
     * Отменяет последнюю выполненную команду.
     * Если история пуста, выводит сообщение в консоль.
     */
    public void undo() {
        if (!history.isEmpty()) {
            Command cmd = history.pop();
            cmd.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }

    /**
     * Выводит текущее содержимое редактора в консоль.
     */
    public void printText() {
        System.out.println("Current Text: \"" + textEditor.getText() + "\"");
    }
}
