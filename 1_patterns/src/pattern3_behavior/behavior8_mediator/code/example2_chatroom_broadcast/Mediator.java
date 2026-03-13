package pattern3_behavior.behavior8_mediator.code.example2_chatroom_broadcast;

/**
 * Интерфейс посредника (Mediator) для чат-комнаты с broadcast-доставкой.
 *
 * <p>В отличие от {@code IChatRoom} из примера 1 (адресная доставка),
 * здесь сообщение отправляется <b>всем</b> зарегистрированным участникам,
 * кроме самого отправителя. Это типичный сценарий группового чата.
 *
 * <p><b>Роль в паттерне:</b> Mediator (интерфейс посредника).
 */
public interface Mediator {

    /**
     * Регистрирует пользователя в чат-комнате.
     *
     * @param user пользователь для регистрации
     */
    void register(User user);

    /**
     * Рассылает сообщение всем участникам, кроме отправителя.
     *
     * @param message текст сообщения
     * @param user    отправитель сообщения
     */
    void sendMessage(String message, User user);
}
