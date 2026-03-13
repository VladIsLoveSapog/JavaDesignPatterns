package pattern3_behavior.behavior8_mediator.code.example2_chatroom_broadcast;

/**
 * Коллега (Colleague) — пользователь чата с broadcast-доставкой.
 *
 * <p>В отличие от примера 1, здесь используется конкретный класс,
 * а не абстрактный: все пользователи ведут себя одинаково (отправляют
 * и получают сообщения), поэтому абстракция не нужна.
 *
 * <p><b>Роль в паттерне:</b> Colleague (конкретный коллега).
 */
public class User {

    /** Имя пользователя, отображаемое в консоли. */
    private String name;

    /**
     * Ссылка на посредника. Устанавливается не в конструкторе, а через
     * {@link #setMediator(Mediator)} при регистрации — посредник сам
     * вызывает этот метод (callback-паттерн).
     */
    private Mediator mediator;

    /**
     * @param name имя пользователя
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Устанавливает ссылку на посредника.
     * <p>Вызывается посредником при регистрации — пользователь не выбирает
     * посредника сам, а получает его через callback.
     *
     * @param mediator посредник, через которого будет происходить общение
     */
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    /**
     * Отправляет сообщение всем участникам чата (через посредника).
     *
     * @param message текст сообщения
     */
    public void send(String message) {
        System.out.println(this.name + " отправил сообщение: " + message);
        // Делегируем рассылку посреднику — пользователь не знает о других участниках
        mediator.sendMessage(message, this);
    }

    /**
     * Получает входящее сообщение от посредника.
     *
     * @param message текст полученного сообщения
     */
    public void receive(String message) {
        System.out.println(this.name + " получил сообщение: " + message);
    }
}
