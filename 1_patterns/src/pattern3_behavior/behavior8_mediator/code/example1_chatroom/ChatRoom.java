package pattern3_behavior.behavior8_mediator.code.example1_chatroom;

import java.util.HashMap;
import java.util.Map;

/**
 * Конкретный посредник (Concrete Mediator) — чат-комната с адресной доставкой.
 *
 * <p>Хранит всех зарегистрированных пользователей в {@code HashMap} и
 * пересылает сообщение конкретному адресату по его идентификатору.
 * Благодаря {@code HashMap} поиск получателя выполняется за O(1).
 *
 * <p><b>Роль в паттерне:</b> Concrete Mediator — знает обо всех коллегах
 * и координирует их взаимодействие, при этом коллеги не знают друг о друге.
 */
public class ChatRoom implements IChatRoom {

    /**
     * Таблица зарегистрированных пользователей: ключ — уникальный ID, значение — объект пользователя.
     * <p>{@code final} — ссылка на коллекцию не может быть перезаписана после создания.
     * <p>{@code HashMap} выбран для быстрого доступа к пользователю по ID за O(1).
     */
    private final Map<String, User> usersMap = new HashMap<>();

    /**
     * Отправляет сообщение пользователю с указанным идентификатором.
     *
     * @param msg    текст сообщения
     * @param userId идентификатор получателя
     */
    @Override
    public void sendMessage(String msg, String userId) {
        // Находим получателя в таблице по ID и передаём ему сообщение
        User u = usersMap.get(userId);
        u.receive(msg);
    }

    /**
     * Регистрирует пользователя в чат-комнате, помещая его в таблицу.
     *
     * @param user пользователь для регистрации
     */
    @Override
    public void addUser(User user) {
        // Сохраняем пользователя в таблицу: ID → объект пользователя
        this.usersMap.put(user.getId(), user);
    }
}
