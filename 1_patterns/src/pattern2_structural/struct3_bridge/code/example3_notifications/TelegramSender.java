package pattern2_structural.struct3_bridge.code.example3_notifications;

/**
 * <b>Роль в паттерне Bridge: Concrete Implementation (Конкретная реализация).</b>
 *
 * <p>Имитирует отправку сообщения через Telegram Bot API. В реальном приложении
 * здесь использовался бы HTTP-клиент для вызова {@code api.telegram.org},
 * но для демонстрации паттерна достаточно вывода в консоль.
 *
 * <p><b>Расширяемость:</b> если завтра потребуется добавить канал WhatsApp или
 * Viber, достаточно создать новый класс, реализующий {@link MessageSender}.
 * Ни {@link TextMessage}, ни {@link HtmlMessage} изменять не придётся —
 * в этом и состоит главное преимущество паттерна Bridge.
 */
public class TelegramSender implements MessageSender {

    /**
     * Отправляет отформатированное сообщение в Telegram-чат.
     *
     * @param recipient        идентификатор получателя, например {@code "@student"}
     * @param formattedMessage сообщение, уже отформатированное стороной Abstraction
     */
    @Override
    public void send(String recipient, String formattedMessage) {
        System.out.println("Отправка Telegram на " + recipient + ": " + formattedMessage);
    }
}
