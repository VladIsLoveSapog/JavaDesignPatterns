package pattern3_behavior.behavior3_command.code.example2_text_editor;

/**
 * Команда удаления текста (роль <b>ConcreteCommand</b>).
 *
 * <p>Инкапсулирует операцию удаления фрагмента текста из {@link TextEditor}.
 *
 * <p><b>Ключевой учебный момент — сохранение состояния для undo:</b>
 * в отличие от {@link InsertCommand}, где для отмены достаточно знать
 * позицию и длину, здесь необходимо запомнить <b>удалённый текст</b>
 * (поле {@code removedText}), чтобы при отмене вставить его обратно.
 * Это фиксация снимка состояния перед необратимым действием.
 *
 * <p><b>Когда использовать:</b> когда операция удаления должна поддерживать
 * отмену — команда сохраняет достаточно данных, чтобы восстановить
 * предыдущее состояние получателя.
 */
public class DeleteCommand implements Command {

    /** Получатель — редактор, из которого удаляется текст. */
    private TextEditor editor;

    /** Позиция начала удаления. */
    private int position;

    /** Количество символов для удаления. */
    private int length;

    /**
     * Текст, который был удалён при последнем вызове {@link #execute()}.
     * Сохраняется для возможности отмены (undo).
     */
    private String removedText;

    /**
     * Создаёт команду удаления.
     *
     * @param editor   получатель — текстовый редактор
     * @param position позиция начала удаления (0-based)
     * @param length   количество символов для удаления
     */
    public DeleteCommand(TextEditor editor, int position, int length) {
        this.editor = editor;
        this.position = position;
        this.length = length;
    }

    /**
     * Удаляет фрагмент текста, предварительно сохраняя его для отмены.
     */
    @Override
    public void execute() {
        removedText = editor.getText().substring(position, position + length);
        editor.delete(position, length);
    }

    /**
     * Отменяет удаление, вставляя ранее удалённый текст обратно.
     */
    @Override
    public void undo() {
        editor.insert(position, removedText);
    }
}
