package pattern2_structural.struct5_decorator.code.example2_message;

/**
 * Конкретный компонент (Concrete Component) — простое текстовое сообщение.
 *
 * <p>Хранит текст и возвращает его как есть. Это базовый объект,
 * который затем можно обернуть декораторами для добавления
 * шифрования, временных меток и другой обработки.
 */
public class SimpleMessage implements Message {

    /** Текст сообщения, заданный при создании. */
    private String content;

    /**
     * Создаёт простое сообщение с указанным текстом.
     *
     * @param content текст сообщения
     */
    public SimpleMessage(String content) {
        this.content = content;
    }

    /**
     * Возвращает исходный текст сообщения без каких-либо модификаций.
     *
     * @return текст сообщения
     */
    @Override
    public String getContent() {
        return content;
    }
}
