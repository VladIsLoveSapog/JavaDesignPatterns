package pattern2_structural.struct3_bridge.code.example3_notifications;

/**
 * <b>Роль в паттерне Bridge: Concrete Implementation (Конкретная реализация).</b>
 *
 * <p>Имитирует отправку сообщения через SMS. В реальном приложении
 * здесь использовался бы SMS-шлюз (например, Twilio API), но для
 * демонстрации паттерна достаточно вывода в консоль.
 *
 * <p><b>Почему это работает без знания формата:</b> метод {@link #send(String, String)}
 * получает уже готовую строку — ему не важно, был ли это plain text или HTML.
 * Решение о формате принимается на стороне Abstraction ({@link Message}).
 */
public class SmsSender implements MessageSender {

    /**
     * Отправляет отформатированное сообщение на указанный номер телефона.
     *
     * @param recipient        номер телефона получателя, например {@code "+7-999-123-45-67"}
     * @param formattedMessage сообщение, уже отформатированное стороной Abstraction
     */
    @Override
    public void send(String recipient, String formattedMessage) {
        System.out.println("Отправка SMS на " + recipient + ": " + formattedMessage);
    }
}
