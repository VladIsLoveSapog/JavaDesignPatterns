package pattern3_behavior.behavior8_mediator.code.example2_chatroom_broadcast;

import java.util.ArrayList;
import java.util.List;

/**
 * Конкретный посредник (Concrete Mediator) — чат-комната с broadcast-доставкой.
 *
 * <p>Хранит список всех участников в {@code ArrayList} и при отправке
 * сообщения итерируется по списку, доставляя сообщение каждому,
 * кроме отправителя. {@code ArrayList} удобен для последовательного
 * обхода всех участников.
 *
 * <p><b>Роль в паттерне:</b> Concrete Mediator — инкапсулирует логику
 * broadcast-рассылки, освобождая коллег от знания друг о друге.
 */
public class ChatRoom implements Mediator {

    /** Список всех зарегистрированных пользователей чата. */
    private List<User> users;

    public ChatRoom() {
        users = new ArrayList<>();
    }

    /**
     * Регистрирует пользователя в чат-комнате.
     * <p>Посредник сам устанавливает ссылку на себя в пользователе
     * через {@link User#setMediator(Mediator)} — это callback-паттерн.
     *
     * @param user пользователь для регистрации
     */
    @Override
    public void register(User user) {
        // Проверяем, что пользователь ещё не зарегистрирован (защита от дублей)
        if (!users.contains(user)) {
            // Добавляем пользователя в список участников
            users.add(user);
            // Посредник устанавливает ссылку на себя — пользователь теперь может отправлять сообщения
            user.setMediator(this);
            // Объявляем о присоединении нового участника
            System.out.println(user.getName() + " присоединился к чат-комнате.");
        }
    }

    /**
     * Рассылает сообщение всем участникам, кроме отправителя.
     *
     * @param message текст сообщения
     * @param sender  отправитель, которому не нужно доставлять его же сообщение
     */
    @Override
    public void sendMessage(String message, User sender) {
        // Итерируемся по всем зарегистрированным пользователям
        for (User user : users) {
            // Пропускаем отправителя — ему не нужно получать собственное сообщение
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}
