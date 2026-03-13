package pattern2_structural.struct5_decorator.code.example2_message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Конкретный декоратор (Concrete Decorator) — добавление временной метки.
 *
 * <p>Оборачивает сообщение и при вызове {@code getContent()} добавляет
 * к тексту текущую дату и время в формате {@code [yyyy-MM-dd HH:mm:ss]}.
 *
 * <p>Этот декоратор можно комбинировать с {@link EncryptedMessageDecorator}:
 * <ul>
 *   <li>{@code new TimestampedMessageDecorator(new EncryptedMessageDecorator(msg))}
 *       — сначала шифрует текст, затем добавляет метку к зашифрованному тексту.</li>
 *   <li>{@code new EncryptedMessageDecorator(new TimestampedMessageDecorator(msg))}
 *       — сначала добавляет метку, затем шифрует всё вместе.</li>
 * </ul>
 * Порядок оборачивания влияет на результат — это важная особенность паттерна.
 */
public class TimestampedMessageDecorator extends MessageDecorator {

    /**
     * @param message сообщение, к которому добавляется временная метка
     */
    public TimestampedMessageDecorator(Message message) {
        super(message);
    }

    /**
     * Получает содержимое обёрнутого сообщения и добавляет к нему
     * текущую дату и время.
     *
     * @return текст сообщения с временной меткой в начале
     */
    @Override
    public String getContent() {
        return addTimestamp(message.getContent());
    }

    /**
     * Формирует строку с текущим временем и добавляет её перед текстом.
     *
     * @param content исходный текст сообщения
     * @return текст с временной меткой вида {@code [2024-01-15 14:30:00] текст}
     */
    private String addTimestamp(String content) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        return "[" + timestamp + "] " + content;
    }
}
