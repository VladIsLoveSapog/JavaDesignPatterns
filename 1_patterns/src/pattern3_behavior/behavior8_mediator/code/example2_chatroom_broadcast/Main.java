package pattern3_behavior.behavior8_mediator.code.example2_chatroom_broadcast;

/**
 * Клиентский код — демонстрация паттерна Посредник (Mediator)
 * на примере чат-комнаты с broadcast-доставкой сообщений.
 *
 * <p>В этом примере каждое сообщение доставляется <b>всем</b> участникам
 * чата, кроме отправителя (групповой чат).
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Посредник (Mediator): Чат-комната с broadcast-доставкой ===\n");

        // 1. Создаём посредника — чат-комнату
        Mediator chatRoom = new ChatRoom();

        // 2. Создаём пользователей (ссылка на посредника будет установлена при регистрации)
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        // 3. Регистрируем пользователей — посредник устанавливает себя через setMediator()
        chatRoom.register(alice);
        chatRoom.register(bob);
        chatRoom.register(charlie);

        System.out.println();

        // 4. Отправляем сообщения — каждое доставляется всем, кроме отправителя
        alice.send("Привет всем!");
        System.out.println();
        bob.send("Привет, Alice!");
        System.out.println();
        charlie.send("Здравствуйте!");
    }
}
