package pattern2_structural.struct5_decorator.code.example2_message;

/**
 * Базовый декоратор (Base Decorator) для сообщений.
 *
 * <p>Реализует интерфейс {@link Message} и хранит ссылку на
 * оборачиваемый объект. По умолчанию просто делегирует вызов
 * {@code getContent()} обёрнутому сообщению — без изменений.
 *
 * <p>Конкретные декораторы ({@link EncryptedMessageDecorator},
 * {@link TimestampedMessageDecorator}) переопределяют методы,
 * добавляя свою логику.
 *
 * <p><b>Зачем нужен базовый декоратор, если можно сразу наследовать
 * от интерфейса?</b> Он избавляет конкретные декораторы от необходимости
 * дублировать код хранения ссылки и делегирования — особенно полезно,
 * когда в интерфейсе много методов.
 */
public abstract class MessageDecorator implements Message {

    /**
     * Ссылка на оборачиваемое сообщение.
     * protected — доступна конкретным декораторам для вызова
     * методов обёрнутого объекта.
     */
    protected Message message;

    /**
     * @param message сообщение, которое будет обёрнуто декоратором
     */
    public MessageDecorator(Message message) {
        this.message = message;
    }

    /**
     * Делегирует вызов обёрнутому сообщению.
     * Конкретные декораторы переопределяют этот метод,
     * добавляя своё поведение.
     *
     * @return содержимое обёрнутого сообщения
     */
    @Override
    public String getContent() {
        return message.getContent();
    }
}
