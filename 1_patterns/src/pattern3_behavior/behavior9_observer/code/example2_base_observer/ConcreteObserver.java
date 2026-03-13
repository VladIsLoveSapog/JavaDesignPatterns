package pattern3_behavior.behavior9_observer.code.example2_base_observer;

/**
 * <b>Конкретный наблюдатель (Concrete Observer)</b>
 *
 * <p>Реализует интерфейс {@link Observer} и определяет конкретную реакцию
 * на уведомления от {@link Subject}.</p>
 *
 * <p>Каждый экземпляр имеет собственное <b>имя</b> ({@code name}), которое
 * используется для идентификации наблюдателя при выводе полученного
 * сообщения в консоль.</p>
 *
 * <p><i>Пример поведения:</i><br>
 * При получении уведомления наблюдатель выводит строку вида:</p>
 * <pre>
 *     имя_наблюдателя получил сообщение: текст_сообщения
 * </pre>
 *
 * @see Observer
 * @see Subject
 */
class ConcreteObserver implements Observer {
    private String name;

    /**
     * Создаёт нового конкретного наблюдателя с указанным именем.
     *
     * @param name имя наблюдателя, используемое для идентификации при выводе сообщений
     */
    public ConcreteObserver(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Выводит в стандартный поток вывода имя наблюдателя и полученное
     * сообщение в формате:</p>
     * <pre>
     *     name получил сообщение: message
     * </pre>
     *
     * @param message текстовое сообщение, описывающее новое состояние субъекта
     */
    @Override
    public void update(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}
