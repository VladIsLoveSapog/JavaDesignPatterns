package pattern2_structural.struct3_bridge.code.example3_notifications;

/**
 * <b>Роль в паттерне Bridge: Concrete Implementation (Конкретная реализация).</b>
 *
 * <p>Имитирует отправку сообщения по электронной почте. В реальном приложении
 * здесь использовался бы {@code javax.mail} или сторонний SMTP-клиент,
 * но для демонстрации паттерна достаточно вывода в консоль.
 *
 * <p><b>Почему отдельный класс:</b> каждый канал доставки инкапсулирует
 * собственную логику подключения и протокол (SMTP, HTTP API, AT-команды).
 * Благодаря Bridge новые каналы добавляются без изменения форматов сообщений
 * ({@link TextMessage}, {@link HtmlMessage}) — достаточно реализовать
 * {@link MessageSender}.
 */
public class EmailSender implements MessageSender {

    /**
     * Отправляет отформатированное сообщение на указанный email-адрес.
     *
     * @param recipient        email-адрес получателя, например {@code "user@example.com"}
     * @param formattedMessage сообщение, уже отформатированное стороной Abstraction
     */
    @Override
    public void send(String recipient, String formattedMessage) {
        System.out.println("Отправка Email на " + recipient + ": " + formattedMessage);
    }
}
