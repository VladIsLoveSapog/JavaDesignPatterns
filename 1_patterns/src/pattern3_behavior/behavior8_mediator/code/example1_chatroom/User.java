package pattern3_behavior.behavior8_mediator.code.example1_chatroom;

/**
 * Абстрактный коллега (Colleague) — базовый класс для всех пользователей чата.
 *
 * <p>Каждый пользователь хранит ссылку на посредника ({@link IChatRoom}),
 * через которого происходит вся коммуникация. Пользователи не знают друг
 * о друге — они обращаются только к посреднику.
 *
 * <p><b>Почему {@code abstract}:</b> класс задаёт общую структуру (поля, геттеры),
 * но делегирует конкретную логику отправки и получения сообщений подклассам.
 * Это позволяет создавать разные типы пользователей (обычный, администратор, бот)
 * с различным поведением.
 *
 * <p><b>Роль в паттерне:</b> Colleague (абстрактный коллега).
 */
public abstract class User {

    /** Ссылка на посредника — единственный канал связи с другими пользователями. */
    private IChatRoom mediator;

    /** Уникальный идентификатор пользователя для адресной доставки сообщений. */
    private String id;

    /** Имя пользователя, отображаемое при выводе сообщений в консоль. */
    private String name;

    /**
     * Создаёт пользователя и привязывает его к чат-комнате.
     *
     * @param room ссылка на посредника (чат-комнату)
     * @param id   уникальный идентификатор пользователя
     * @param name отображаемое имя пользователя
     */
    public User(IChatRoom room, String id, String name) {
        this.mediator = room;
        this.name = name;
        this.id = id;
    }

    /** Отправляет сообщение другому пользователю через посредника. */
    public abstract void send(String msg, String userId);

    /** Получает входящее сообщение от посредника. */
    public abstract void receive(String msg);

    public IChatRoom getMediator() {
        return mediator;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
