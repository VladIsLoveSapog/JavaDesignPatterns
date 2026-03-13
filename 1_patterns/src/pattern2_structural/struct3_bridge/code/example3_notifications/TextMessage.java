package pattern2_structural.struct3_bridge.code.example3_notifications;

/**
 * <b>Роль в паттерне Bridge: Refined Abstraction (Уточнённая абстракция).</b>
 *
 * <p>Форматирует сообщение как обычный текст (plain text): заголовок и тело
 * разделяются символом новой строки. После форматирования делегирует
 * отправку каналу доставки через {@link MessageSender#send(String, String)}.
 *
 * <p><b>Почему форматирование — на стороне абстракции:</b> формат сообщения
 * (текст, HTML, Markdown) — это ответственность абстракции. Канал доставки
 * (Email, SMS, Telegram) не должен знать о формате. Это и есть суть
 * разделения, которое обеспечивает паттерн Bridge.
 */
public class TextMessage extends Message {

    /**
     * Создаёт текстовое сообщение с указанным каналом доставки.
     *
     * @param sender канал доставки ({@link MessageSender})
     * @param title  заголовок сообщения
     * @param body   основной текст сообщения
     */
    public TextMessage(MessageSender sender, String title, String body) {
        super(sender, title, body);
    }

    /**
     * Форматирует сообщение как plain text и отправляет через канал {@link #sender}.
     *
     * <p>Формат: {@code "заголовок\nтело"}.
     *
     * @param recipient адрес/номер/идентификатор получателя
     */
    @Override
    public void send(String recipient) {
        String formatted = title + "\n" + body;
        sender.send(recipient, formatted);
    }
}
