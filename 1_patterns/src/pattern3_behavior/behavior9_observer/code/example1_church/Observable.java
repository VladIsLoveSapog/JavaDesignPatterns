package pattern3_behavior.behavior9_observer.code.example1_church;

/**
 * <b>Observable</b> (Наблюдаемое) — интерфейс издателя (Subject) в паттерне <i>Observer</i>.
 *
 * <p>Определяет контракт для управления подписчиками ({@link Observer}):
 * <ul>
 *     <li>регистрация нового подписчика;</li>
 *     <li>удаление существующего подписчика;</li>
 *     <li>оповещение всех зарегистрированных подписчиков об изменениях.</li>
 * </ul>
 *
 * <p>Конкретная реализация — {@link CatholicChurch}.
 *
 * @see Observer
 * @see CatholicChurch
 */
public interface Observable {
    /**
     * Регистрация подписчика.
     *
     * <p>Добавляет переданного {@link Observer} в список рассылки,
     * чтобы он получал уведомления при вызове {@link #notifyObservers()}.
     *
     * @param observer подписчик, который будет зарегистрирован на получение оповещений.
     */
    void registerObserver(Observer observer);

    /**
     * Удаление подписчика.
     *
     * <p>Исключает переданного {@link Observer} из списка рассылки.
     *
     * @param observer подписчик, который больше не будет получать оповещения.
     */
    void removeObserver(Observer observer);

    /**
     * Оповещение всех зарегистрированных подписчиков.
     *
     * <p>Вызывает метод {@link Observer#update(String)} у каждого подписчика,
     * передавая актуальные данные.
     */
    void notifyObservers();
}
