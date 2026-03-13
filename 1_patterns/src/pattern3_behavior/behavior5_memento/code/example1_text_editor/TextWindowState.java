package pattern3_behavior.behavior5_memento.code.example1_text_editor;

/**
 * <b>Снимок (Memento)</b> текстового окна.
 *
 * <p>Хранит неизменяемую копию текста, которая была актуальна на момент создания снимка.
 * Объект снимка полностью иммутабелен: после создания его состояние невозможно изменить,
 * что гарантирует целостность сохранённых данных.</p>
 *
 * <p>Роль в паттерне: <b>Memento (Снимок)</b> — объект, инкапсулирующий
 * внутреннее состояние {@link TextWindow Создателя}.</p>
 *
 * @see TextWindow Создатель (Originator)
 * @see TextEditor Опекун (Caretaker)
 */
public class TextWindowState {
    /** Неизменяемая строка — снимок должен быть полностью иммутабельным. */
    private final String text;

    /**
     * Создаёт снимок с указанным текстом.
     *
     * @param text текст, актуальный на момент сохранения
     */
    public TextWindowState(String text) {
        this.text = text;
    }

    /**
     * Возвращает сохранённый текст.
     *
     * @return текст на момент создания снимка
     */
    public String getText() {
        return text;
    }
}
