package pattern2_structural.struct5_decorator.code.example2_message;

/**
 * Конкретный декоратор (Concrete Decorator) — шифрование сообщения.
 *
 * <p>Оборачивает любое сообщение и при вызове {@code getContent()}
 * возвращает «зашифрованный» текст. Для простоты шифрование реализовано
 * как разворот строки (в реальном проекте здесь был бы AES, RSA и т.д.).
 *
 * <p>Обратите внимание: декоратор работает с интерфейсом {@link Message},
 * поэтому он может обернуть как {@link SimpleMessage}, так и другой
 * декоратор — например, {@link TimestampedMessageDecorator}.
 */
public class EncryptedMessageDecorator extends MessageDecorator {

    /**
     * @param message сообщение, которое нужно зашифровать
     */
    public EncryptedMessageDecorator(Message message) {
        super(message);
    }

    /**
     * Получает содержимое обёрнутого сообщения и шифрует его.
     *
     * @return зашифрованный текст сообщения
     */
    @Override
    public String getContent() {
        return encrypt(message.getContent());
    }

    /**
     * Простая симуляция шифрования: разворот строки задом наперёд.
     * В учебных целях этого достаточно для демонстрации принципа.
     *
     * @param content исходный текст
     * @return «зашифрованный» текст (строка наоборот)
     */
    private String encrypt(String content) {
        return new StringBuilder(content).reverse().toString();
    }
}
