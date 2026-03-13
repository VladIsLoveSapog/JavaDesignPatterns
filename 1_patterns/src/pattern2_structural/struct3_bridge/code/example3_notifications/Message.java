package pattern2_structural.struct3_bridge.code.example3_notifications;

/**
 * <b>Роль в паттерне Bridge: Abstraction (Абстракция).</b>
 *
 * <p>Абстрактный класс, описывающий сообщение с заголовком и телом.
 * Поле {@link #sender} — это и есть «мост» (bridge) к стороне реализации:
 * через него абстракция делегирует фактическую доставку конкретному каналу
 * ({@link EmailSender}, {@link SmsSender}, {@link TelegramSender}).
 *
 * <p><b>Почему абстрактный класс, а не интерфейс:</b> абстракции нужны общие поля
 * ({@code sender}, {@code title}, {@code body}) и конструктор для их инициализации.
 * Интерфейс в Java не может хранить изменяемое состояние, поэтому абстрактный
 * класс здесь — единственный правильный выбор.
 *
 * <p><b>Как работает мост:</b> подклассы ({@link TextMessage}, {@link HtmlMessage})
 * переопределяют {@link #send(String)}, форматируя сообщение по-своему, а затем
 * вызывают {@code sender.send(recipient, formatted)}. Таким образом формат
 * сообщения и канал доставки изменяются независимо друг от друга.
 *
 * <p><b>Без Bridge</b> пришлось бы создавать отдельный класс для каждой комбинации
 * формата и канала: {@code TextEmail}, {@code TextSms}, {@code TextTelegram},
 * {@code HtmlEmail}, {@code HtmlSms}, {@code HtmlTelegram} — итого 6 классов.
 * С Bridge достаточно 2 формата + 3 канала = 5 классов + 1 интерфейс + 1 абстракция = 7.
 */
public abstract class Message {

    /**
     * Мост к реализации — конкретный канал доставки сообщения.
     *
     * <p>{@code protected} — подклассы должны иметь доступ для делегирования
     * вызова {@link MessageSender#send(String, String)}.
     */
    protected MessageSender sender;

    /** Заголовок сообщения. */
    protected String title;

    /** Тело (основной текст) сообщения. */
    protected String body;

    /**
     * Создаёт сообщение с указанным каналом доставки, заголовком и телом.
     *
     * @param sender канал доставки ({@link MessageSender}), через который будет отправлено сообщение
     * @param title  заголовок сообщения
     * @param body   основной текст сообщения
     */
    public Message(MessageSender sender, String title, String body) {
        this.sender = sender;
        this.title = title;
        this.body = body;
    }

    /**
     * Форматирует сообщение и отправляет его получателю через канал {@link #sender}.
     *
     * <p>Каждый подкласс определяет собственный формат (plain text, HTML и т.д.),
     * а затем делегирует доставку реализации.
     *
     * @param recipient адрес/номер/идентификатор получателя
     */
    public abstract void send(String recipient);

    /**
     * Позволяет сменить канал доставки во время выполнения программы (runtime).
     *
     * <p><b>Почему это важно:</b> благодаря композиции (а не наследованию)
     * канал можно заменить без пересоздания объекта сообщения.
     *
     * @param sender новый канал доставки
     */
    public void setSender(MessageSender sender) {
        this.sender = sender;
    }
}
