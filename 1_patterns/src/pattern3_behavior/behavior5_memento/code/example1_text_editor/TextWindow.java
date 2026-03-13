package pattern3_behavior.behavior5_memento.code.example1_text_editor;

/**
 * <b>Создатель (Originator)</b> — текстовое окно.
 *
 * <p>Объект, чьё внутреннее состояние (текст) мы хотим сохранять и восстанавливать.
 * Для хранения текста используется {@link StringBuilder} — изменяемая строка,
 * которая позволяет эффективно добавлять текст.</p>
 *
 * <p>Роль в паттерне: <b>Originator (Создатель)</b> — объект, который умеет
 * создавать снимки своего состояния ({@link #save()}) и восстанавливаться
 * из ранее сохранённого снимка ({@link #restore(TextWindowState)}).</p>
 *
 * @see TextWindowState Снимок (Memento)
 * @see TextEditor Опекун (Caretaker)
 */
public class TextWindow {
    /** Изменяемый текст окна ({@link StringBuilder}). */
    private StringBuilder currentText;

    /**
     * Создаёт пустое текстовое окно.
     */
    public TextWindow() {
        this.currentText = new StringBuilder();
    }

    /**
     * Добавляет текст к текущему содержимому окна.
     *
     * @param text текст для добавления
     */
    public void addText(String text) {
        currentText.append(text);
    }

    /**
     * Возвращает текущее содержимое окна.
     *
     * @return {@link StringBuilder} с текущим текстом
     */
    public StringBuilder getCurrentText() {
        return currentText;
    }

    /**
     * Создаёт снимок текущего состояния текстового окна.
     *
     * <p>Текст копируется в неизменяемый объект {@link TextWindowState},
     * чтобы дальнейшие изменения в окне не затронули снимок.</p>
     *
     * @return снимок текущего текста
     */
    public TextWindowState save() {
        return new TextWindowState(currentText.toString());
    }

    /**
     * Восстановление состояния текстового окна из снимка.
     *
     * <p>Текущий текст заменяется содержимым переданного снимка.</p>
     *
     * @param save снимок, к которому необходимо вернуться
     */
    public void restore(TextWindowState save) {
        currentText = new StringBuilder(save.getText());
    }
}
