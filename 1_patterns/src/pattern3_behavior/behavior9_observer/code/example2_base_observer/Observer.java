package pattern3_behavior.behavior9_observer.code.example2_base_observer;

/**
 * <b>Интерфейс Observer (Наблюдатель)</b>
 *
 * <p>Определяет контракт для всех конкретных наблюдателей, которые хотят
 * получать уведомления от объекта {@link Subject}.</p>
 *
 * <p>Использует <b>push-модель</b> уведомлений: субъект самостоятельно
 * передаёт сообщение типа {@code String} каждому наблюдателю через
 * метод {@link #update(String)}.</p>
 *
 * <p><i>Роль в паттерне Observer:</i></p>
 * <ol>
 *     <li>Объявляет единственный метод обратного вызова {@link #update(String)}</li>
 *     <li>Позволяет {@link Subject} работать с наблюдателями через абстракцию,
 *         не зная их конкретных типов</li>
 * </ol>
 *
 * @see Subject
 * @see ConcreteObserver
 */
interface Observer {

    /**
     * Вызывается субъектом ({@link Subject}) при изменении его состояния.
     *
     * <p>Каждый конкретный наблюдатель реализует этот метод, определяя
     * собственную реакцию на полученное уведомление.</p>
     *
     * @param message текстовое сообщение, описывающее новое состояние субъекта
     * @see Subject#notifyObservers()
     */
    void update(String message);
}
