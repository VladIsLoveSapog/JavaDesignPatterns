package pattern3_behavior.behavior3_command.code.example2_text_editor;

/**
 * Текстовый редактор — получатель команд (роль <b>Receiver</b>).
 *
 * <p>Содержит бизнес-логику редактирования текста: вставку и удаление
 * символов по позиции. Конкретные команды ({@link InsertCommand},
 * {@link DeleteCommand}) делегируют выполнение этому классу.
 *
 * <p><b>Когда использовать:</b> когда бизнес-логика редактирования
 * должна быть отделена от механизма вызова, истории и отмены операций.
 */
public class TextEditor {

    /** Текущее содержимое редактора. */
    private StringBuilder text = new StringBuilder();

    /**
     * Вставляет текст в указанную позицию.
     *
     * @param position позиция вставки (0-based)
     * @param content  текст для вставки
     * @throws IllegalArgumentException если позиция выходит за допустимые границы
     */
    public void insert(int position, String content) {
        if (position < 0 || position > text.length())
            throw new IllegalArgumentException("Invalid position");

        text.insert(position, content);
        System.out.println("Inserted \"" + content + "\" at position " + position);
    }

    /**
     * Удаляет фрагмент текста начиная с указанной позиции.
     *
     * @param position начальная позиция удаления (0-based)
     * @param length   количество символов для удаления
     * @throws IllegalArgumentException если позиция или длина выходят за допустимые границы
     */
    public void delete(int position, int length) {
        if (position < 0 || position + length > text.length())
            throw new IllegalArgumentException("Invalid position or length");

        String removed = text.substring(position, position + length);
        text.delete(position, position + length);
        System.out.println("Deleted \"" + removed + "\" from position " + position);
    }

    /**
     * Возвращает текущее содержимое редактора.
     *
     * @return текст в редакторе
     */
    public String getText() {
        return text.toString();
    }
}
