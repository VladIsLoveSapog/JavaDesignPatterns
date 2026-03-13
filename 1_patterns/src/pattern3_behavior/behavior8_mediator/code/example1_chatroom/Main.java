package pattern3_behavior.behavior8_mediator.code.example1_chatroom;

/**
 * Клиентский код — демонстрация паттерна Посредник (Mediator)
 * на примере чат-комнаты с адресной доставкой сообщений.
 *
 * <p>В этом примере сообщения доставляются конкретному пользователю
 * по его идентификатору, а не всем участникам чата (broadcast).
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Посредник (Mediator): Чат-комната с адресной доставкой ===\n");

        // 1. Создаём посредника — чат-комнату
        IChatRoom chatroom = new ChatRoom();

        // 2. Создаём пользователей, передавая им ссылку на посредника
        User user1 = new ChatUser(chatroom, "1", "Alex");
        User user2 = new ChatUser(chatroom, "2", "Brian");
        User user3 = new ChatUser(chatroom, "3", "Charles");
        User user4 = new ChatUser(chatroom, "4", "David");

        // 3. Регистрируем пользователей в чат-комнате
        chatroom.addUser(user1);
        chatroom.addUser(user2);
        chatroom.addUser(user3);
        chatroom.addUser(user4);

        // 4. Отправляем адресные сообщения через посредника
        user1.send("Привет, Brian!", "2");   // Alex → Brian (по ID "2")
        System.out.println();
        user2.send("Привет, дружище!", "1");  // Brian → Alex (по ID "1")
    }
}
