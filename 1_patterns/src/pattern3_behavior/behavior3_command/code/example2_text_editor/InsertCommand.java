package pattern3_behavior.behavior3_command.code.example2_text_editor;

/**
 * Команда вставки текста (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует операцию вставки текста в {@link TextEditor}.
 * Хранит все параметры, необходимые для выполнения и отмены:
 * ссылку на получателя, позицию и вставляемый контент.
 *
 * <p><b>Отмена (undo):</b> для отмены вставки достаточно удалить
 * ровно столько символов, сколько было вставлено, начиная с той же позиции.
 *
 * <p><b>Когда использовать:</b> когда операция вставки должна быть
 * представлена как объект для постановки в очередь, логирования
 * или поддержки отмены.
 */
public class InsertCommand implements Command {

    /** Получатель — редактор, в который вставляется текст. */
    private TextEditor editor;

    /** Позиция вставки. */
    private int position;

    /** Текст для вставки. */
    private String content;

    /**
     * Создаёт команду вставки.
     *
     * @param editor   получатель — текстовый редактор
     * @param position позиция вставки (0-based)
     * @param content  текст для вставки
     */
    public InsertCommand(TextEditor editor, int position, String content) {
        this.editor = editor;
        this.position = position;
        this.content = content;
    }

    /** Вставляет текст в редактор. */
    @Override
    public void execute() {
        editor.insert(position, content);
    }

    /** Отменяет вставку, удаляя вставленный фрагмент. */
    @Override
    public void undo() {
        editor.delete(position, content.length());
    }
}
