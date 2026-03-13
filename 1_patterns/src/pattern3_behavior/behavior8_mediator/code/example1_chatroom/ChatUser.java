package pattern3_behavior.behavior8_mediator.code.example1_chatroom;

/**
 * Конкретный коллега (Concrete Colleague) — обычный пользователь чата.
 *
 * <p>Реализует логику отправки и получения сообщений. При отправке
 * пользователь не обращается к получателю напрямую — вместо этого
 * он делегирует доставку посреднику ({@link IChatRoom}).
 *
 * <p><b>Роль в паттерне:</b> Concrete Colleague.
 */
public class ChatUser extends User {

    /**
     * @param room ссылка на посредника (чат-комнату)
     * @param id   уникальный идентификатор пользователя
     * @param name отображаемое имя пользователя
     */
    public ChatUser(IChatRoom room, String id, String name) {
        super(room, id, name);
    }

    /**
     * Отправляет сообщение другому пользователю через посредника.
     *
     * @param msg    текст сообщения
     * @param userId идентификатор получателя
     */
    @Override
    public void send(String msg, String userId) {
        System.out.println(this.getName() + " :: Отправка сообщения: " + msg);
        // Делегируем доставку посреднику — отправитель не знает, кто такой получатель
        getMediator().sendMessage(msg, userId);
    }

    /**
     * Получает входящее сообщение, переданное посредником.
     *
     * @param msg текст полученного сообщения
     */
    @Override
    public void receive(String msg) {
        System.out.println(this.getName() + " :: Получено сообщение: " + msg);
    }
}
