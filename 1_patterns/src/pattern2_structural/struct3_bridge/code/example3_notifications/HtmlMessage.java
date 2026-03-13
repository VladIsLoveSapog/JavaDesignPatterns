package pattern2_structural.struct3_bridge.code.example3_notifications;

/**
 * <b>Роль в паттерне Bridge: Refined Abstraction (Уточнённая абстракция).</b>
 *
 * <p>Форматирует сообщение с HTML-тегами: заголовок оборачивается в {@code <h1>},
 * тело — в {@code <p>}. После форматирования делегирует отправку каналу доставки
 * через {@link MessageSender#send(String, String)}.
 *
 * <p><b>Почему отдельный класс, а не параметр «format»:</b> каждый формат может
 * иметь собственную сложную логику (экранирование спецсимволов, вложенные теги,
 * шаблоны). Отдельный класс соблюдает принцип Single Responsibility и позволяет
 * расширять набор форматов (Markdown, XML) без изменения существующего кода.
 */
public class HtmlMessage extends Message {

    /**
     * Создаёт HTML-сообщение с указанным каналом доставки.
     *
     * @param sender канал доставки ({@link MessageSender})
     * @param title  заголовок сообщения
     * @param body   основной текст сообщения
     */
    public HtmlMessage(MessageSender sender, String title, String body) {
        super(sender, title, body);
    }

    /**
     * Форматирует сообщение с HTML-тегами и отправляет через канал {@link #sender}.
     *
     * <p>Формат: {@code "<h1>заголовок</h1><p>тело</p>"}.
     *
     * @param recipient адрес/номер/идентификатор получателя
     */
    @Override
    public void send(String recipient) {
        String formatted = "<h1>" + title + "</h1><p>" + body + "</p>";
        sender.send(recipient, formatted);
    }
}
